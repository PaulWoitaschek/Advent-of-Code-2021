package aoc.year2023

import aoc.library.Puzzle
import aoc.library.lcm

object Day20 : Puzzle<Long, Long>(day = 20) {

  private data class Instruction(val low: Boolean, val from: String, val to: String)

  override fun solvePart1(input: String): Long {
    val modules = parse(input)

    var lowPulses = 0L
    var highPulses = 0L

    repeat(1000) {
      val result = pressButton(modules)
      lowPulses += result.lowPulses
      highPulses += result.highPulses
    }

    return lowPulses * highPulses
  }

  override fun solvePart2(input: String): Long {
    val modules = parse(input)

    val conjunctionToRx = modules.single { "rx" in it.destinations }
    val valuesForHighPulse = modules.filter { conjunctionToRx.name in it.destinations }
      .associate { it.name to 0 }
      .toMutableMap()

    var buttonPresses = 0
    while (valuesForHighPulse.any { (_, value) -> value == 0 }) {
      buttonPresses++
      pressButton(modules) { conjunctionName ->
        if (conjunctionName in valuesForHighPulse) {
          valuesForHighPulse[conjunctionName] = buttonPresses
        }
      }
    }
    return lcm(valuesForHighPulse.values.map(Int::toLong))
  }

  private data class ButtonPressResult(val lowPulses: Int, val highPulses: Int)

  private fun pressButton(
    modules: List<Module>,
    onHighPulseConjunction: (String) -> Unit = {},
  ): ButtonPressResult {
    var lowPulses = 0
    var highPulses = 0
    val byName = modules.associateBy { it.name }
    val queue = mutableListOf(Instruction(low = true, from = "button", to = "broadcaster"))
    while (queue.isNotEmpty()) {
      val instruction = queue.removeFirst()
      if (instruction.low) {
        lowPulses++
      } else {
        highPulses++
      }
      when (val module = byName[instruction.to]) {
        is Module.Broadcaster -> {
          module.destinations.mapTo(queue) { destination ->
            Instruction(low = instruction.low, to = destination, from = module.name)
          }
        }
        is Module.Conjunction -> {
          module.memory[instruction.from] = !instruction.low
          val hasOnlyHighPulses = module.memory.all { it.value }
          if (!hasOnlyHighPulses) {
            onHighPulseConjunction(module.name)
          }
          module.destinations.mapTo(queue) { destination ->
            Instruction(low = hasOnlyHighPulses, to = destination, from = module.name)
          }
        }
        is Module.FlipFlow -> {
          if (instruction.low) {
            module.on = !module.on
            module.destinations.mapTo(queue) { destination ->
              Instruction(low = !module.on, to = destination, from = module.name)
            }
          }
        }
        null -> {
          // ignore, testing module
        }
      }
    }
    return ButtonPressResult(lowPulses, highPulses)
  }

  private sealed interface Module {
    val name: String
    val destinations: List<String>

    data class FlipFlow(var on: Boolean, override val name: String, override val destinations: List<String>) : Module

    data class Conjunction(override val name: String, override val destinations: List<String>, val memory: MutableMap<String, Boolean>) :
      Module

    data class Broadcaster(override val destinations: List<String>) : Module {
      override val name: String = "broadcaster"
    }
  }

  private fun parse(input: String): List<Module> {
    val modules = input.lines()
      .map { line ->
        val (moduleValue, destinationsValue) = line.split(" -> ")
        val destinations = destinationsValue.split(", ")
        when {
          moduleValue.startsWith('%') -> Module.FlipFlow(
            on = false,
            name = moduleValue.drop(1),
            destinations = destinations,
          )
          moduleValue == "broadcaster" -> Module.Broadcaster(destinations)
          moduleValue.startsWith("&") -> Module.Conjunction(
            name = moduleValue.drop(1),
            destinations = destinations,
            memory = mutableMapOf(),
          )
          else -> error("Invalid module $moduleValue")
        }
      }

    modules.forEach { module ->
      if (module is Module.Conjunction) {
        modules.forEach { other ->
          if (module.name in other.destinations) {
            module.memory[other.name] = false
          }
        }
      }
    }

    return modules
  }
}

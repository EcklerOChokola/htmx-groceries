package net.eckler_o_chokola.htmx_table_demo.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import jakarta.servlet.http.HttpServletResponse

import net.eckler_o_chokola.htmx_table_demo.model.Item
import net.eckler_o_chokola.htmx_table_demo.services.ItemService

@Controller
@RequestMapping("/table")
class Table(private val itemService: ItemService) {

	@GetMapping
	fun getTable(): String {
		println("Requested table element")
		return "table"
	}
	
	@GetMapping("/loading")
	fun getTableLoading(): String {
		return "table/loading"
	}

	@GetMapping("/content")
	fun getTableContent(model: Model, response: HttpServletResponse): String {
		println("Requested table content")
		model.addAttribute("items", itemService.list())
		response.setHeader("HX-Trigger", "loadStats")

		return "table/content"
	}

	@GetMapping("/stats")
	fun getTableStats(response: HttpServletResponse): ResponseEntity<Int> {
		response.setHeader("HX-Trigger", "loaded")
		println("Requested table stats")
		return ResponseEntity.ok(
			itemService.count()
		)
	}

	@GetMapping("/form")
	fun getTableForm(): String {
		println("Requested table form")
		return "table/form"
	}

	@DeleteMapping("/form")
	fun resetTableForm(): String {
		println("Requested reset table form")
		return "table/add-line"
	}

	@PostMapping("/entry")
	fun addTableEntry(
		@RequestParam name: String, 
		@RequestParam count: Int,
		response: HttpServletResponse
	): String {
		println("Adding entry with name=$name and count=$count")
		response.setHeader("HX-Trigger", "reloadTable")
		itemService.add(Item(name, count))

		return "empty"
	}
}

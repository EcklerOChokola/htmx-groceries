package net.eckler_o_chokola.htmx_table_demo.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.ResponseEntity
import jakarta.servlet.http.HttpServletResponse
import net.eckler_o_chokola.htmx_table_demo.services.ItemService

@Controller
@RequestMapping("/button")
class Button(private val itemService: ItemService) {
	
	@GetMapping("/refresh/loading")
	fun getLoadingRefreshButton(response: HttpServletResponse): String {
		println("Requested button refresh loading")
		response.setHeader("HX-Trigger", "reloadTable")
		return "button/refresh/loading"
	}
	
	@GetMapping("/refresh/loaded")
	fun getLoadedRefreshButton(): String {
		println("Requested button refresh loaded")
		return "button/refresh/loaded"
	}

	@GetMapping("/reset/loading")
	fun getLoadingResetButton(response: HttpServletResponse): String {
		println("Requested button reset loading")
		response.setHeader("HX-Trigger", "reloadTable")
		itemService.reset()

		return "button/reset/loading"
	}
	
	@GetMapping("/reset/loaded")
	fun getLoadedResetButton(): String {
		println("Requested button reset loaded")
		return "button/reset/loaded"
	}

}

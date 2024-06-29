package net.eckler_o_chokola.htmx_table_demo.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Home {

	@GetMapping("/")
	fun getHomePage(): String {
		println("Requested index page")
		return "index"
	}

}

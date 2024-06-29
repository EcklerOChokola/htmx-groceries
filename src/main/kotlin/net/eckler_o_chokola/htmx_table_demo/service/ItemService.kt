package net.eckler_o_chokola.htmx_table_demo.services

import org.springframework.stereotype.Service
import net.eckler_o_chokola.htmx_table_demo.model.Item

@Service
class ItemService {
	private val items: MutableList<Item> = mutableListOf()

	fun add(item: Item) = items.add(item)

	fun list() = items

	fun count(): Int = items.count()

	fun reset() = items.clear()

}

package com.maxmayr.warehouseservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class WarehouseServiceApplication: SpringBootServletInitializer()

fun main(args: Array<String>) {
	runApplication<WarehouseServiceApplication>(*args)
}

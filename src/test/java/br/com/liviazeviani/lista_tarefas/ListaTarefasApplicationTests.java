package br.com.liviazeviani.lista_tarefas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.liviazeviani.lista_tarefas.entity.Todo;

@SpringBootTest
class ListaTarefasApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("nome", "description", false, 2);

		webTestClient
		.post()
		.uri("/todos")
		.bodyValue(todo)
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$").isArray()
		.jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$[0].name").isEqualTo(todo.getName())
		.jsonPath("$[0].description").isEqualTo(todo.getDescription())
		.jsonPath("$[0].realized").isEqualTo(todo.isRealized())
		.jsonPath("$[0].priorization").isEqualTo(todo.getPriorization());

	}

	@Test
	void testCreateTodoFailure() {
	}

}

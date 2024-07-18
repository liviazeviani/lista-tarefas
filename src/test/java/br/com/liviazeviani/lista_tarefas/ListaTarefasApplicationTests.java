package br.com.liviazeviani.lista_tarefas;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.liviazeviani.lista_tarefas.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
		webTestClient
		.post().uri("/todos")
		.bodyValue(new Todo(
			 "",
			"",
			false,
			0))
			.exchange()
			.expectStatus().isBadRequest();

	}

	@Test
void testDeleteTodoSuccess() {
    
    Todo todo = new Todo("name", "description", false, 5);

    Todo createdTodo = webTestClient.post()
            .uri("/todos")
            .bodyValue(todo)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Todo.class)
            .returnResult().getResponseBody();

    assertNotNull(createdTodo);
    Long todoId = createdTodo.getId();
    assertNotNull(todoId);

    webTestClient.delete()
            .uri("/todos/{id}", todoId)
            .exchange()
            .expectStatus().isOk();

    webTestClient.get()
            .uri("/todos/{id}", todoId)
            .exchange()
            .expectStatus().isNotFound();
}

}

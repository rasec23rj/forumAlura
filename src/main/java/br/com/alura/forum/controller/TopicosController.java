package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

@Controller
@ResponseBody
@RequestMapping("/topicos")
public class TopicosController {

	@RequestMapping()
	public List<TopicoDto> lista() {
		Topico topico = new Topico("Dúdiva", "Dúdiva com Spring", new Curso("Spring", "Programação"));
		return TopicoDto.converter(Arrays.asList(topico, topico));
	}

}

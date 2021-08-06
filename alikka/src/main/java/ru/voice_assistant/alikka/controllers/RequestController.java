package ru.voice_assistant.alikka.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.voice_assistant.alikka.models.Module;
import ru.voice_assistant.alikka.models.Request;
import ru.voice_assistant.alikka.models.Response;
import ru.voice_assistant.alikka.repositories.ModuleRepository;
import ru.voice_assistant.alikka.repositories.RequestRepository;
import ru.voice_assistant.alikka.repositories.ResponseRepository;
import ru.voice_assistant.alikka.tools.JarExecutor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
public class RequestController {

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	ResponseRepository responseRepository;

	@Value("${modules.path}")
	String modulesPath;

	@Autowired
	ModuleRepository moduleRepository;

	@PostMapping("/request")
	public Response handleRequest(@RequestBody Request request) throws Exception {
		System.out.println(request.getCommand());

		// get command
		// find module by command
		Module module = moduleRepository.findByCommandsContaining(request.getCommand());

		// execute module
		JarExecutor jarExecutor = new JarExecutor();
		jarExecutor.executeJar(modulesPath + "/" + module.getPath(), new ArrayList<>());
		jarExecutor.getExecutionLog();
		String result = jarExecutor.getOutput();
		JSONObject json = new JSONObject(result);

		return new Response(request, json.getString("text"), "OK");
	}
}

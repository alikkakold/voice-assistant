package ru.voice_assistant.alikka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voice_assistant.alikka.models.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}

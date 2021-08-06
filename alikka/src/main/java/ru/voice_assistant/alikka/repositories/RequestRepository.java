package ru.voice_assistant.alikka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voice_assistant.alikka.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}

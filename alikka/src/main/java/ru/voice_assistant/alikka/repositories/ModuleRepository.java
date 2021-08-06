package ru.voice_assistant.alikka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voice_assistant.alikka.models.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
	Module findByCommandsContaining(String command);
}

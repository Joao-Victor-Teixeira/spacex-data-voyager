package com.joaodev.spacex_api.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaodev.spacex_api.models.entities.Rocket;
import com.joaodev.spacex_api.repositories.RocketRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    private final RocketRepository rocketRepository;

    public DatabaseSeeder(RocketRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        

        if (rocketRepository.count() == 0) {
            System.out.println("🌱 Semeando dados de teste no MongoDB...");

            Rocket r1 = new Rocket("seed-1", "Falcon 9 (Seed)", true, "Foguete de teste via Seed");
            Rocket r2 = new Rocket("seed-2", "Starship (Seed)", false, "Protótipo de teste via Seed");

            rocketRepository.saveAll(Arrays.asList(r1, r2));
            
            System.out.println("✅ Seed finalizado com sucesso!");
        } else {
            System.out.println("ℹ️ MongoDB já possui dados, pulando Seed.");
        }
    }
}

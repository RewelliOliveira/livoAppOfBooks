package com.example.livoappofbooks.utils

data class ReadingMessage(
    val title: String,
    val message: String
)

object ReadingMessages {
    val list = listOf(

        // --- As Clássicas ---
        ReadingMessage(
            title = "Hora da Leitura 📖",
            message = "Já leu algumas páginas hoje? Seu livro te espera!"
        ),
        ReadingMessage(
            title = "Pausa Produtiva ☕",
            message = "Que tal desconectar um pouco e avançar um capítulo?"
        ),
        ReadingMessage(
            title = "Onde você parou? 🤔",
            message = "Descubra o que acontece na próxima página agora mesmo."
        ),
        ReadingMessage(
            title = "Alimente sua mente 🧠",
            message = "Ler é o melhor exercício para o cérebro. Pratique!"
        ),
        ReadingMessage(
            title = "Livo App 🐢",
            message = "Mantenha o hábito! 5 minutos de leitura já fazem diferença."
        ),

        // --- Humor & Ironia ---
        ReadingMessage(
            title = "Só mais um... 👀",
            message = "A maior mentira que contam os leitores: 'só mais um capítulo'."
        ),
        ReadingMessage(
            title = "Cadê você? 👻",
            message = "Seu livro perguntou se você sumiu ou se foi abduzido."
        ),
        ReadingMessage(
            title = "Alerta de Spoiler 🚨",
            message = "Se você demorar muito, alguém vai te contar o final..."
        ),
        ReadingMessage(
            title = "Terapia Grátis 🛋️",
            message = "Ler sai mais barato que terapia (na maioria das vezes)."
        ),
        ReadingMessage(
            title = "Cliffhanger 😱",
            message = "Vai mesmo deixar o personagem pendurado nesse perigo?"
        ),
        ReadingMessage(
            title = "Prioridades 💅",
            message = "Louça pra lavar? Esqueça. O livro é mais importante."
        ),
        ReadingMessage(
            title = "Sono é para os fracos 🐼",
            message = "Quem precisa dormir quando se tem um mistério para resolver?"
        ),

        // --- Curtas & Diretas ---
        ReadingMessage(
            title = "Psiu! 🤫",
            message = "Seu marcador de página está com saudades."
        ),
        ReadingMessage(
            title = "Agora 📖",
            message = "Abra. Leia. Continue."
        ),
        ReadingMessage(
            title = "Ei 👋",
            message = "Capítulo novo te esperando."
        ),
        ReadingMessage(
            title = "Tempo Roubado ⏳",
            message = "5 min de leitura. Só isso."
        ),
        ReadingMessage(
            title = "Silêncio 🤫",
            message = "A história vai continuar."
        ),

        // --- Desafios ---
        ReadingMessage(
            title = "Desafio do Dia ⚔️",
            message = "Que tal ler 10 páginas antes de checar as redes sociais?"
        ),
        ReadingMessage(
            title = "Missão Simples 🎯",
            message = "Um capítulo. Depois você decide."
        ),
        ReadingMessage(
            title = "Speedrun Literária 🏃",
            message = "Quanto você lê em 10 minutos?"
        ),
        ReadingMessage(
            title = "Teste de Força 💪",
            message = "Leia agora ou admita a derrota."
        ),

        // --- Humor + Ironia (novas) ---
        ReadingMessage(
            title = "Coragem 😌",
            message = "Abra o livro. O capítulo não morde."
        ),
        ReadingMessage(
            title = "Procrastinação Literária ⏰",
            message = "Você já abriu tudo hoje… menos o livro."
        ),
        ReadingMessage(
            title = "Climinha de Culpa 😬",
            message = "Seu livro está ali… parado… esperando…"
        ),
        ReadingMessage(
            title = "Só Observando 👀",
            message = "A gente sabe que você tem tempo pra ler agora."
        ),
        ReadingMessage(
            title = "Desculpa Criativa 🤡",
            message = "‘Depois eu leio’ também conta como fantasia."
        ),
        ReadingMessage(
            title = "Relacionamento Tóxico 💔",
            message = "Você promete que vai ler… e nunca aparece."
        ),

        // --- Curtas + Humor (perfeitas pra push) ---
        ReadingMessage(
            title = "Plot Twist 😏",
            message = "Você tem tempo pra ler. Surpresa!"
        ),
        ReadingMessage(
            title = "Autoengano 🤥",
            message = "‘Só vou ver uma coisa rápida’."
        ),
        ReadingMessage(
            title = "Drama 📚",
            message = "O personagem sofre. Você some."
        ),
        ReadingMessage(
            title = "Leitor Fantasma 👻",
            message = "Apareça. O livro sente sua falta."
        ),
        ReadingMessage(
            title = "Crítica Social 🧐",
            message = "Menos tela. Mais história."
        )
    )
}

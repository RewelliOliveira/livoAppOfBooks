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
            title = "Ei 👋",
            message = "Capítulo novo te esperando."
        ),
        ReadingMessage(
            title = "Tempo Roubado ⏳",
            message = "5 min de leitura. Só isso."
        ),

        // --- Desafios ---
        ReadingMessage(
            title = "Desafio do Dia ⚔️",
            message = "Que tal ler 10 páginas antes de checar as redes sociais?"
        ),
        ReadingMessage(
            title = "Speedrun Literária 🏃",
            message = "Quanto você lê em 10 minutos?"
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
        ),
        ReadingMessage("Silêncio Absoluto 🤫", "Não é regra. É julgamento."),
        ReadingMessage("Desde 1983 📚", "Já vi leitores melhores… mas sigo com esperança."),
        ReadingMessage("Calma, Jovem ⏳", "O livro não foge. Diferente do seu foco."),
        ReadingMessage("Antigamente… 🧓", "Líamos sem Wi-Fi. E sobrevivemos."),
        ReadingMessage("Respeito 📖", "Esse livro tem mais conteúdo que sua timeline."),
        ReadingMessage("Observação 🧐", "Virar páginas ainda funciona. Testado."),
        ReadingMessage("Tradição 📚", "Abrir um livro é um hábito milenar. Tente."),
        ReadingMessage("Experiência Própria 🧠", "Quem lê mais reclama menos. Geralmente."),
        ReadingMessage("Educação Básica 📖", "Começa abrindo o livro."),
        ReadingMessage("Silêncio Interno 🫠", "É só você, o livro e sua consciência."),

        ReadingMessage("Sem Pressa ⌛", "O livro só está esperando. Julgando. Mas esperando."),
        ReadingMessage("Curioso 🤔", "Você lembra por que começou esse livro?"),
        ReadingMessage("Constatação 📝", "Mais um dia sem ler. Fascinante."),
        ReadingMessage("Nota Mental 🧠", "Você prometeu ler hoje também."),
        ReadingMessage("Lamento 📚", "O capítulo terminou sozinho. Sem você."),
        ReadingMessage("Registro Histórico 📜", "Dia X: o leitor sumiu novamente."),
        ReadingMessage("Compromisso 💍", "Você e esse livro… complicado."),
        ReadingMessage("Expectativa 📖", "Baixa. Mas ainda existe."),
        ReadingMessage("Rotina 📆", "Você abre tudo. Menos o livro."),
        ReadingMessage("Só Observando 👀", "Eu vi você rolando a tela. Vi tudo."),

        ReadingMessage("Milagre 📚", "Ainda dá tempo de ler hoje. Surpreenda-me."),
        ReadingMessage("Choque Cultural ⚡", "Letras. Muitas letras. Assustador, eu sei."),
        ReadingMessage("Modernidade 🤳", "Arraste o dedo… nas páginas."),
        ReadingMessage("Atualização 📖", "O livro continua exatamente onde você abandonou."),
        ReadingMessage("Drama Desnecessário 🎭", "É só um capítulo. Respire."),
        ReadingMessage("Força Interior 💪", "Você consegue virar páginas. Acredite."),
        ReadingMessage("Tecnologia Antiga 🗞️", "Funciona sem bateria."),
        ReadingMessage("Risco Controlado ⚠️", "Ler não dói. Normalmente."),
        ReadingMessage("Surpresa 🎁", "A história avança quando você lê."),
        ReadingMessage("Mistério Resolvido 🕵️", "O problema nunca foi falta de tempo."),

        ReadingMessage("Respeite 📚", "Esse livro foi impresso para ser lido."),
        ReadingMessage("Tristeza 📘", "Páginas intocadas… que desperdício."),
        ReadingMessage("Orgulho 📖", "Livros gostam de leitores comprometidos."),
        ReadingMessage("Cuidado 💼", "Histórias abandonadas criam ressentimento."),
        ReadingMessage("Conselho 🧓", "Leia agora. Reclame depois."),
        ReadingMessage("Afeto Disfarçado 🤍", "O livro ainda acredita em você."),
        ReadingMessage("Vínculo 📘", "Vocês eram tão próximos… lembra?"),
        ReadingMessage("Saudade 📚", "O marcador se sente inútil."),
        ReadingMessage("Promessa 📖", "Só um capítulo. Palavra de bibliotecário."),
        ReadingMessage("Tradição Oral 📜", "Leitores antigos não abandonavam histórias."),

        ReadingMessage("Ei. 📖", "Leia."),
        ReadingMessage("Agora. 📚", "Sem desculpas."),
        ReadingMessage("Silêncio. 🤫", "Hora do livro."),
        ReadingMessage("Página Aberta 📘", "Você sabe o que fazer."),
        ReadingMessage("Último Aviso ⏳", "Ou lê, ou arrepende."),
        ReadingMessage("Constatação 🧐", "Você tem 5 minutos."),
        ReadingMessage("Realidade 📖", "O livro ainda existe."),
        ReadingMessage("Fato 📚", "Histórias não se leem sozinhas."),
        ReadingMessage("Escolha Difícil 🤔", "Scroll ou capítulo?"),
        ReadingMessage("Decência 📘", "Leia um pouco."),

        ReadingMessage("Anotado 📝", "Mais um dia sem ler."),
        ReadingMessage("Decepção Controlada 😐", "Eu esperava menos. Mesmo assim…"),
        ReadingMessage("Histórico 📜", "Você sempre diz “amanhã”."),
        ReadingMessage("Franqueza 🧓", "O livro merecia alguém melhor."),
        ReadingMessage("Observação Técnica 🧐", "Tempo você tem. Vontade…"),
        ReadingMessage("Clareza 📖", "Isso não vai se ler sozinho."),
        ReadingMessage("Registro Oficial 📚", "Leitor ausente. Novamente."),
        ReadingMessage("Suspiro Profundo 😮‍💨", "Leia. Por favor."),
        ReadingMessage("Última Chance ⏰", "Depois não reclame do spoiler."),
        ReadingMessage("Silêncio Julgador 🤫", "Estou olhando pra você."),

        ReadingMessage("Transparência 👀", "Sabemos que você está com o celular na mão."),
        ReadingMessage("Coincidência 🤔", "Notificação apareceu bem na sua folga…"),
        ReadingMessage("Curioso 📱", "Abriu isso, mas não o livro."),
        ReadingMessage("Tecnologia 🧠", "Esse aviso não apareceu por acaso."),
        ReadingMessage("Análise Comportamental 📊", "Você evita capítulos longos."),
        ReadingMessage("Previsível 📖", "Você ia ignorar. Mas leu isso."),
        ReadingMessage("Plot Twist 😏", "Você ainda pode ler agora."),
        ReadingMessage("Estatística 📈", "Leitores que leem… terminam livros."),
        ReadingMessage("Choque de Realidade ⚡", "O livro continua melhor que o feed."),
        ReadingMessage("Consciência 📚", "Você sabe que eu estou certo."),

        ReadingMessage("Sem Briga 🤍", "Leia no seu ritmo. Mas leia."),
        ReadingMessage("Velha Sabedoria 🧓", "Um capítulo por dia não mata."),
        ReadingMessage("Companheirismo 📖", "Livros são pacientes. Até certo ponto."),
        ReadingMessage("Esperança 📘", "Hoje pode ser diferente."),
        ReadingMessage("Confiança Moderada 😌", "Acredito em você. Um pouco."),
        ReadingMessage("Convite 📚", "Sente-se. Leia. Fique."),
        ReadingMessage("Respeitosamente 🤫", "Hora do livro."),
        ReadingMessage("Despedida Temporária 👋", "Volte quando estiver lendo."),
        ReadingMessage("Promessa 📖", "A história vale a pena."),
        ReadingMessage("Assinado ✍️", "— O Velho Bibliotecário")

    )
}

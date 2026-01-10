package com.example.livoappofbooks.data.mock

import com.example.livoappofbooks.data.model.Book

object PopularBooks {
    // Mapa de categorias para listas de livros "famosos" usados como mock
    val popularByCategory: Map<String, List<Book>> = mapOf(
        "Clássicos" to listOf(
            Book(
                id = "1984",
                title = "1984",
                authors = listOf("George Orwell"),
                publishedDate = "1949",
                pageCount = 328,
                averageRating = 4.17,
                thumbnail = "https://covers.openlibrary.org/b/isbn/0451524934-L.jpg"
            ),
            Book(
                id = "pride_and_prejudice",
                title = "Pride and Prejudice",
                authors = listOf("Jane Austen"),
                publishedDate = "1813",
                pageCount = 279,
                averageRating = 4.25,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780141439518-L.jpg"
            ),
            Book(
                id = "the_great_gatsby",
                title = "The Great Gatsby",
                authors = listOf("F. Scott Fitzgerald"),
                publishedDate = "1925",
                pageCount = 180,
                averageRating = 3.92,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780743273565-L.jpg"
            ),
            Book(
                id = "moby_dick",
                title = "Moby-Dick",
                authors = listOf("Herman Melville"),
                publishedDate = "1851",
                pageCount = 635,
                averageRating = 3.50,
                thumbnail = "https://covers.openlibrary.org/b/isbn/0142437247-L.jpg"
            ),
            Book(
                id = "crime_and_punishment",
                title = "Crime and Punishment",
                authors = listOf("Fyodor Dostoevsky"),
                publishedDate = "1866",
                pageCount = 671,
                averageRating = 4.21,
                thumbnail = "https://covers.openlibrary.org/b/isbn/0140449132-L.jpg"
            )
        ),

        "Ficção Popular" to listOf(
            Book(
                id = "to_kill_a_mockingbird",
                title = "To Kill a Mockingbird",
                authors = listOf("Harper Lee"),
                publishedDate = "1960",
                pageCount = 281,
                averageRating = 4.28,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780060935467-L.jpg"
            ),
            Book(
                id = "hp_sorcerers_stone",
                title = "Harry Potter and the Sorcerer's Stone",
                authors = listOf("J.K. Rowling"),
                publishedDate = "1997",
                pageCount = 223,
                averageRating = 4.47,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780590353427-L.jpg"
            ),
            Book(
                id = "the_da_vinci_code",
                title = "The Da Vinci Code",
                authors = listOf("Dan Brown"),
                publishedDate = "2003",
                pageCount = 454,
                averageRating = 3.84,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780307474278-L.jpg"
            ),
            Book(
                id = "gone_girl",
                title = "Gone Girl",
                authors = listOf("Gillian Flynn"),
                publishedDate = "2012",
                pageCount = 422,
                averageRating = 4.06,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780307588371-L.jpg"
            ),
            Book(
                id = "the_alchemist",
                title = "The Alchemist",
                authors = listOf("Paulo Coelho"),
                publishedDate = "1988",
                pageCount = 208,
                averageRating = 3.86,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780061122415-L.jpg"
            )
        ),

        "Ficção Científica" to listOf(
            Book(
                id = "dune",
                title = "Dune",
                authors = listOf("Frank Herbert"),
                publishedDate = "1965",
                pageCount = 412,
                averageRating = 4.20,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780441172719-L.jpg"
            ),
            Book(
                id = "foundation",
                title = "Foundation",
                authors = listOf("Isaac Asimov"),
                publishedDate = "1951",
                pageCount = 255,
                averageRating = 4.16,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780553293357-L.jpg"
            ),
            Book(
                id = "enders_game",
                title = "Ender's Game",
                authors = listOf("Orson Scott Card"),
                publishedDate = "1985",
                pageCount = 324,
                averageRating = 4.30,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780812550702-L.jpg"
            ),
            Book(
                id = "neuromancer",
                title = "Neuromancer",
                authors = listOf("William Gibson"),
                publishedDate = "1984",
                pageCount = 271,
                averageRating = 3.90,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780441569595-L.jpg"
            )
        ),

        "Não-ficção" to listOf(
            Book(
                id = "sapiens",
                title = "Sapiens: A Brief History of Humankind",
                authors = listOf("Yuval Noah Harari"),
                publishedDate = "2011",
                pageCount = 443,
                averageRating = 4.37,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780062316097-L.jpg"
            ),
            Book(
                id = "educated",
                title = "Educated",
                authors = listOf("Tara Westover"),
                publishedDate = "2018",
                pageCount = 334,
                averageRating = 4.47,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780399590504-L.jpg"
            ),
            Book(
                id = "the_power_of_habit",
                title = "The Power of Habit",
                authors = listOf("Charles Duhigg"),
                publishedDate = "2012",
                pageCount = 371,
                averageRating = 4.06,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780812981605-L.jpg"
            )
        ),

        "Mistério & Suspense" to listOf(
            Book(
                id = "the_girl_with_the_dragon_tattoo",
                title = "The Girl with the Dragon Tattoo",
                authors = listOf("Stieg Larsson"),
                publishedDate = "2005",
                pageCount = 465,
                averageRating = 4.11,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780307454546-L.jpg"
            ),
            Book(
                id = "sherlock_holmes",
                title = "The Complete Sherlock Holmes",
                authors = listOf("Arthur Conan Doyle"),
                publishedDate = "1892",
                pageCount = 1280,
                averageRating = 4.53,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780140439083-L.jpg"
            ),
            Book(
                id = "big_little_lies",
                title = "Big Little Lies",
                authors = listOf("Liane Moriarty"),
                publishedDate = "2014",
                pageCount = 460,
                averageRating = 4.10,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780399167065-L.jpg"
            )
        ),

        "Romance" to listOf(
            Book(
                id = "jane_eyre",
                title = "Jane Eyre",
                authors = listOf("Charlotte Brontë"),
                publishedDate = "1847",
                pageCount = 500,
                averageRating = 4.12,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780142437201-L.jpg"
            ),
            Book(
                id = "wuthering_heights",
                title = "Wuthering Heights",
                authors = listOf("Emily Brontë"),
                publishedDate = "1847",
                pageCount = 416,
                averageRating = 3.87,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780141439556-L.jpg"
            ),
            Book(
                id = "the_notebook",
                title = "The Notebook",
                authors = listOf("Nicholas Sparks"),
                publishedDate = "1996",
                pageCount = 214,
                averageRating = 3.86,
                thumbnail = "https://covers.openlibrary.org/b/isbn/9780446605236-L.jpg"
            )
        )
    )

    // Retorna uma cópia do mapa com as listas embaralhadas — útil para UI que quer randomizar a ordem
    fun shuffled(): Map<String, List<Book>> = popularByCategory.mapValues { it.value.shuffled() }
}

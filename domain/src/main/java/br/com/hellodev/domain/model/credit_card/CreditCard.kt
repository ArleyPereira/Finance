package br.com.hellodev.domain.model.credit_card

data class CreditCard(
    val id: String? = null,
    val name: String? = null,
    val image: String? = null,
    val limit: Float = 0f,
    val closingDay: Int = 0,
    val dueDate: String? = null
) {
    companion object {
        val items = listOf(
            CreditCard(
                id = "1",
                name = "Nubank",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Fnubank.png?alt=media&token=66fff6f9-b13a-41fe-b551-98e034531b57",
                limit = 4000f,
                closingDay = 10,
                dueDate = "17"
            ),
            CreditCard(
                id = "2",
                name = "PicPay",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Fpicpay.png?alt=media&token=8b478ded-9646-4e4e-81d9-c7ff933a8284",
                limit = 2500f,
                closingDay = 5,
                dueDate = "12"
            ),
            CreditCard(
                id = "3",
                name = "Inter",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Finter.png?alt=media&token=5a2c7184-72b1-4553-9a44-0d6850e6533a",
                limit = 6000f,
                closingDay = 8,
                dueDate = "15"
            ),
            CreditCard(
                id = "4",
                name = "Neon",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Fneon.png?alt=media&token=a4c78d80-986e-45c5-a6ae-ceb583aaa6fb",
                limit = 3000f,
                closingDay = 3,
                dueDate = "10"
            ),
            CreditCard(
                id = "5",
                name = "C6 Bank",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Fc6bank.png?alt=media&token=741a8cd0-daa9-4da0-8cf7-883cac88cf3a",
                limit = 5000f,
                closingDay = 9,
                dueDate = "16"
            ),
            CreditCard(
                id = "6",
                name = "Banco Original",
                image = "https://fakeimg.com/original.png",
                limit = 3200f,
                closingDay = 7,
                dueDate = "14"
            ),
            CreditCard(
                id = "7",
                name = "Banco Pan",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Fpan.png?alt=media&token=a77d1c3b-615d-464a-bf63-f6ffccfbe8d0",
                limit = 1800f,
                closingDay = 4,
                dueDate = "11"
            ),
            CreditCard(
                id = "8",
                name = "Santander SX",
                image = "https://fakeimg.com/santander.png",
                limit = 4200f,
                closingDay = 6,
                dueDate = "13"
            ),
            CreditCard(
                id = "9",
                name = "Méliuz",
                image = "https://firebasestorage.googleapis.com/v0/b/finance-251ac.firebasestorage.app/o/images%2Fbanks%2Fmeliuz.png?alt=media&token=7472cb24-2073-44d7-a7ed-bdfb2778983f",
                limit = 3500f,
                closingDay = 2,
                dueDate = "09"
            ),
            CreditCard(
                id = "10",
                name = "Itaú Click",
                image = "https://fakeimg.com/itau.png",
                limit = 4500f,
                closingDay = 1,
                dueDate = "08"
            )
        )
    }
}

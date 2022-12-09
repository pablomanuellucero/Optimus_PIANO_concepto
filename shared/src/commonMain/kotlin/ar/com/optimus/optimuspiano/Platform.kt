package ar.com.optimus.optimuspiano

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
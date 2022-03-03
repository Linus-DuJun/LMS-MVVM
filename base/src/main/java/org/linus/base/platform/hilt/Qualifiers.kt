package org.linus.lms.hilt.qualifiers

import javax.inject.Qualifier

// Retrofit for LMS api
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LmsRetrofit

//Retrofit for TMDB api
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LmsOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TmdbOkHttpClient
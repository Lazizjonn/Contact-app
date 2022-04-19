package uz.gita.contactappretrofit.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappretrofit.domain.repository.Repository
import uz.gita.contactappretrofit.domain.repository.impl.RepositoryImpl
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @[Singleton Binds]
    fun getRepository(impl: RepositoryImpl): Repository

}
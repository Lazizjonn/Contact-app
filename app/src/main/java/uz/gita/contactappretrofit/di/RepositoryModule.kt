package uz.gita.contactappretrofit.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappretrofit.domain.repository.AppRepository
import uz.gita.contactappretrofit.domain.repository.impl.AppRepositoryImpl
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @[Singleton Binds]
    fun getRepository(implApp: AppRepositoryImpl): AppRepository

}
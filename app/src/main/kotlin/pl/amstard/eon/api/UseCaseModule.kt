package pl.amstard.eon.api

import dagger.Module
import dagger.Provides
import pl.amstard.eon.communications.CommunicationManager
import pl.amstard.eon.usecases.RepoUseCase
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides @Singleton
    fun provideRepoUseCase(communicationManager: CommunicationManager): RepoUseCase {
        return RepoUseCase(communicationManager)
    }
}
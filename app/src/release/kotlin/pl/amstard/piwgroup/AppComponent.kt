package pl.amstard.piwgroup

import dagger.Component
import pl.amstard.piwgroup.api.CoreApiModule
import pl.amstard.piwgroup.api.ReleaseApiModule
import pl.amstard.piwgroup.api.StorageModule
import pl.amstard.piwgroup.screens.competences.CompetencesComponent
import pl.amstard.piwgroup.screens.competences.CompetencesModule
import pl.amstard.piwgroup.screens.search.result.SearchResultComponent
import pl.amstard.piwgroup.screens.search.result.SearchResultModule
import pl.amstard.piwgroup.screens.search.result.WorkerItemView
import pl.amstard.piwgroup.screens.workers.WorkersComponent
import pl.amstard.piwgroup.screens.workers.WorkersModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, CoreApiModule::class, StorageModule::class, ReleaseApiModule::class))
interface AppComponent: AppGraph, AppDependencies {

}

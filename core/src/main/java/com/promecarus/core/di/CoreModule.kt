@file:Suppress("SpellCheckingInspection")

package com.promecarus.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.promecarus.core.BuildConfig
import com.promecarus.core.data.database.GuccaDatabase
import com.promecarus.core.data.preference.SettingPreferenceImpl
import com.promecarus.core.data.remote.ApiService
import com.promecarus.core.data.repository.FavoriteRepositoryImpl
import com.promecarus.core.data.repository.HistoryRepositoryImpl
import com.promecarus.core.data.repository.SettingRepositoryImpl
import com.promecarus.core.data.repository.UserRepositoryImpl
import com.promecarus.core.domain.interactor.DeleteFavoriteInteractor
import com.promecarus.core.domain.interactor.DeleteHistoryInteractor
import com.promecarus.core.domain.interactor.GetAllFavoriteIdInteractor
import com.promecarus.core.domain.interactor.GetAllFavoriteInteractor
import com.promecarus.core.domain.interactor.GetAllHistoryInteractor
import com.promecarus.core.domain.interactor.GetDetailInteractor
import com.promecarus.core.domain.interactor.GetFollowersInteractor
import com.promecarus.core.domain.interactor.GetFollowingInteractor
import com.promecarus.core.domain.interactor.GetSettingInteractor
import com.promecarus.core.domain.interactor.InsertFavoriteInteractor
import com.promecarus.core.domain.interactor.InsertHistoryInteractor
import com.promecarus.core.domain.interactor.SearchInteractor
import com.promecarus.core.domain.interactor.SetSettingInteractor
import com.promecarus.core.domain.preference.SettingPreference
import com.promecarus.core.domain.repository.FavoriteRepository
import com.promecarus.core.domain.repository.HistoryRepository
import com.promecarus.core.domain.repository.SettingRepository
import com.promecarus.core.domain.repository.UserRepository
import com.promecarus.core.domain.usecase.DeleteFavoriteUseCase
import com.promecarus.core.domain.usecase.DeleteHistoryUseCase
import com.promecarus.core.domain.usecase.GetAllFavoriteIdUseCase
import com.promecarus.core.domain.usecase.GetAllFavoriteUseCase
import com.promecarus.core.domain.usecase.GetAllHistoryUseCase
import com.promecarus.core.domain.usecase.GetDetailUseCase
import com.promecarus.core.domain.usecase.GetFollowersUseCase
import com.promecarus.core.domain.usecase.GetFollowingUseCase
import com.promecarus.core.domain.usecase.GetSettingUseCase
import com.promecarus.core.domain.usecase.InsertFavoriteUseCase
import com.promecarus.core.domain.usecase.InsertHistoryUseCase
import com.promecarus.core.domain.usecase.SearchUseCase
import com.promecarus.core.domain.usecase.SetSettingUseCase
import com.promecarus.core.ui.viewmodel.FavoriteViewModel
import com.promecarus.core.ui.viewmodel.HomeViewModel
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoreModule {
    val Context.setting by preferencesDataStore(name = "setting")

    val databaseModule = module {
        single {
            Room.databaseBuilder(
                context = get(),
                klass = GuccaDatabase::class.java,
                name = "database-gucca",
            ).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO HistoryEntity (query, timestamp) VALUES ('promecarus', 1708383877628)")
                }
            })
                .openHelperFactory(SupportFactory(SQLiteDatabase.getBytes(BuildConfig.API_KEY.toCharArray())))
                .build()
        }
    }

    val preferenceModule = module {
        single<SettingPreference> {
            SettingPreferenceImpl(dataStore = androidContext().setting)
        }
    }

    val networkModule = module {
        single {
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE))
                        .certificatePinner(
                            CertificatePinner.Builder().add(
                                BuildConfig.BASE_URL.substringAfter("https://").removeSuffix("/"),
                                "sha256/jFaeVpA8UQuidlJkkpIdq3MPwD0m8XbuCRbJlezysBE=",
                                "sha256/Jg78dOE+fydIGk19swWwiypUSR6HWZybfnJG/8G7pyM=",
                                "sha256/e0IRz5Tio3GA1Xs4fUVWmH1xHDiH2dMbVtCBSkOIdqM=",
                                "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=",
                            ).build()
                        ).build()
                ).build().create(ApiService::class.java)

        }
    }

    val repositoryModule = module {
        single<FavoriteRepository> {
            FavoriteRepositoryImpl(guccaDatabase = get())
        }

        single<HistoryRepository> {
            HistoryRepositoryImpl(guccaDatabase = get())
        }

        single<SettingRepository> {
            SettingRepositoryImpl(settingPreference = get())
        }

        single<UserRepository> {
            UserRepositoryImpl(apiService = get())
        }
    }

    val useCaseModule = module {
        single<DeleteFavoriteUseCase> {
            DeleteFavoriteInteractor(favoriteRepository = get())
        }

        single<DeleteHistoryUseCase> {
            DeleteHistoryInteractor(historyRepository = get())
        }

        single<GetAllFavoriteIdUseCase> {
            GetAllFavoriteIdInteractor(favoriteRepository = get())
        }

        single<GetAllFavoriteUseCase> {
            GetAllFavoriteInteractor(favoriteRepository = get())
        }

        single<GetAllHistoryUseCase> {
            GetAllHistoryInteractor(historyRepository = get())
        }

        single<GetDetailUseCase> {
            GetDetailInteractor(userRepository = get())
        }

        single<GetFollowersUseCase> {
            GetFollowersInteractor(
                settingRepository = get(),
                userRepository = get(),
            )
        }

        single<GetFollowingUseCase> {
            GetFollowingInteractor(
                settingRepository = get(),
                userRepository = get(),
            )
        }

        single<GetSettingUseCase> {
            GetSettingInteractor(settingRepository = get())
        }

        single<InsertFavoriteUseCase> {
            InsertFavoriteInteractor(favoriteRepository = get())
        }

        single<InsertHistoryUseCase> {
            InsertHistoryInteractor(historyRepository = get())
        }

        single<SearchUseCase> {
            SearchInteractor(
                settingRepository = get(),
                userRepository = get(),
            )
        }

        single<SetSettingUseCase> {
            SetSettingInteractor(settingRepository = get())
        }
    }

    val viewModelModule = module {
        viewModel {
            FavoriteViewModel(
                deleteFavoriteUseCase = get(),
                getAllFavoriteIdUseCase = get(),
                getAllFavoriteUseCase = get(),
                getDetailUseCase = get(),
                getFollowersUseCase = get(),
                getFollowingUseCase = get(),
                insertFavoriteUseCase = get(),
            )
        }

        viewModel {
            HomeViewModel(
                deleteFavoriteUseCase = get(),
                deleteHistoryUseCase = get(),
                getAllFavoriteIdUseCase = get(),
                getAllHistoryUseCase = get(),
                getDetailUseCase = get(),
                getFollowersUseCase = get(),
                getFollowingUseCase = get(),
                getSettingUseCase = get(),
                insertFavoriteUseCase = get(),
                insertHistoryUseCase = get(),
                searchUseCase = get(),
                setSettingUseCase = get(),
            )
        }
    }
}

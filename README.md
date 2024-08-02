# CountryApp

CountryApp is an Android application that showcases a list of countries along with their flags, names, and capitals. Upon clicking a country, a dialog displays additional details including the flag, name, continent, currency, capital, and languages spoken. The application follows the MVVM architecture and Clean Architecture principles to ensure a modular, maintainable, and testable codebase. The app uses modern Android development tools and libraries, including GraphQL for networking and Hilt for dependency injection.

## Table of Contents

- [Screenshots](#screenshots)
- [Features](#features)
- [Architecture](#architecture)
  - [Data Layer](#data-layer)
  - [Domain Layer](#domain-layer)
  - [Presentation Layer](#presentation-layer)
- [Libraries](#libraries)
- [Setup](#setup)
- [Usage](#usage)

## Screenshots

### Country List Screen
![countires](https://github.com/user-attachments/assets/fc06e67a-2b32-43f1-877e-8127287a784c)

### Country Details Dialog
![dialog](https://github.com/user-attachments/assets/f813cf7a-6b46-4eec-b7a2-8adf4712eecf)

## Features

- Display a list of countries with their flags, names, and capitals.
- Show a detailed dialog with additional country information on click.
- Dependency injection with Hilt.
- Networking with GraphQL and Apollo.

## Architecture

The application follows the MVVM (Model-View-ViewModel) architecture pattern and Clean Architecture principles. The project is divided into three main layers: Data, Domain, and Presentation.

### Data Layer

The Data layer is responsible for fetching data from various sources (network, cache, etc.) and providing it to the Domain layer. It includes:

- **API**: Defines the GraphQL queries and mutations for the country API.
- **Mappers**: Maps data from network models to domain models.
- **Models**: Contains the data models used in the Data layer.

#### Example

```kotlin
// ApolloCountryClient.kt
class ApolloCountryClient(  
  private val apolloClient: ApolloClient,  
) : CountryClient {  
  override suspend fun getCountries(): List<SimpleCountry> =  
    apolloClient  
    .query(CountriesQuery())  
    .execute()  
    .data  
    ?.countries  
    ?.map { it.toSimpleCountry() }  
    ?: emptyList()  
}

// CountryMappers.kt
fun CountryQuery.Country.toDetailCountry(): DetailCountry =  
  DetailCountry(  
    code = code,  
    name = name,  
    emoji = emoji,  
    capital = capital ?: "No capital",  
    currency = currency ?: "No currency",  
    languages = languages.map { it.name },  
    continent = continent.name,  
  )
```

### Domain Layer

The Domain layer contains the business logic of the application. It includes:

- **Models**: The core data models used throughout the application.
- **Datasource**: Interfaces that define the methods for data operations.

#### Example

```kotlin
// Country.kt
data class SimpleCountry(  
  val code: String,  
  val name: String,  
  val emoji: String,  
  val capital: String,  
)

// GetCountriesUSeCase.kt
class GetCountriesUSeCase(  
  private val countryClient: CountryClient,  
) {  
  suspend fun execute(): List<SimpleCountry> =  
    countryClient  
    .getCountries()  
    .sortedBy { it.name }  
}
```

### Presentation Layer

The Presentation layer is responsible for displaying data to the user and handling user interactions. It includes:

- **Theme**: Defines the UI theme for the application.
- **UI**: Contains the composables for the UI.

#### Example

```kotlin
// CountriesViewModel.kt
@HiltViewModel  
class CountriesViewModel  
@Inject  
constructor(  
  private val getCountriesUseCase: GetCountriesUSeCase,  
  private val getCountryUseCase: GetCountryUseCase,  
) : ViewModel() {  
  private val _state = MutableStateFlow(CountriesState())  
  val state: StateFlow<CountriesState> = _state.asStateFlow()  
  
  init {  
    viewModelScope.launch {  
      _state.update {  
        it.copy(  
          isLoading = true,  
        )  
      }  
      _state.update {  
        it.copy(  
          countries = getCountriesUseCase.execute(),  
          isLoading = false,  
        )  
      }  
    }  
  }
}

// CountriesScreen.kt
@Composable  
fun CountriesScreen(viewModel: CountriesViewModel = hiltViewModel()) {  
  val state by viewModel.state.collectAsState()  
  CountriesContent(  
    state = state,  
    onSelectCountry = viewModel::selectCountry,  
    onDismissCountryDialog = viewModel::dismissCountryDialog,  
  )  
}
```

## Libraries

- **[Hilt](https://dagger.dev/hilt/)**: For dependency injection.
- **[GraphQL](https://graphql.org/)**: For querying data.

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/CountryApp.git
   ```

2. Open the project in Android Studio.

3. Build the project to install the necessary dependencies.

5. Run the application on an emulator or physical device.

## Usage

- Launch the app to see the list of countries.
- Click on a country to see detailed information in a dialog.
- Navigate back to the list of countries.

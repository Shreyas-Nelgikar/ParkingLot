# Parking Lot Management System

A comprehensive parking lot management system built in Java that handles vehicle entry, exit, billing, and slot assignment operations.

## 🏗️ System Architecture

This system follows a layered architecture with clear separation of concerns:

```
├── Controllers     # Handle HTTP-like requests and responses
├── Services        # Business logic implementation
├── Repositories    # Data access layer
├── Models          # Domain entities
├── DTOs           # Data transfer objects
├── Strategies     # Strategy pattern implementations
└── Exceptions     # Custom exception handling
```

## 📋 Features

- **Token Management**: Issue entry tokens for vehicles
- **Slot Assignment**: Automatic parking slot allocation using configurable strategies
- **Bill Generation**: Calculate parking fees and generate bills
- **Multiple Vehicle Types**: Support for different vehicle categories
- **Payment Processing**: Handle various payment methods
- **Gate Management**: Entry and exit gate operations
- **Multi-floor Support**: Manage parking across multiple floors

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- IDE with Lombok plugin support

### Installation

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
   ```bash
   mvn clean compile
   ```
4. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

## 🏛️ Core Components

### Models
- **ParkingLot**: Main parking facility with floors and gates
- **Token**: Entry token issued to vehicles
- **Bill**: Exit bill with payment details
- **Vehicle**: Vehicle information and details
- **User**: Customer information
- **Gate**: Entry/exit points with operators
- **ParkingSlot**: Individual parking spaces

### Services
- **TokenService**: Handles token issuance and validation
- **BillService**: Manages bill generation and payment processing
- **ParkingLotService**: Manages parking lot operations and slot availability
- **PaymentService**: Processes payments using different strategies
- **UserService**: Manages user operations
- **VehicleService**: Handles vehicle registration and management
- **GateService**: Manages gate operations

### Strategies
- **SlotAssignmentStrategy**: Interface for slot assignment algorithms
  - `RandomSlotAssignmentStrategy`: Assigns slots randomly
- **PricingStrategy**: Interface for pricing calculations
  - `VehicleTypePricingStrategy`: Pricing based on vehicle type

### Controllers
- **TokenController**: Handles token-related requests
- **BillController**: Manages billing operations

## 🔄 System Flow

### Vehicle Entry Process
1. User provides vehicle and personal details
2. System validates user and vehicle information
3. Gate details are verified
4. Available parking slot is assigned using strategy
5. Entry token is generated and issued

### Vehicle Exit Process
1. User presents token at exit gate
2. System validates token and gate details
3. Parking fee is calculated based on duration and vehicle type
4. Payment is processed
5. Bill is generated and parking slot is released

## 💾 Data Management

The system uses in-memory repositories for data storage:
- `UserRepository`: User information
- `VehicleDetailsRepository`: Vehicle details
- `TokenRepository`: Active tokens
- `BillRepository`: Generated bills
- `ParkingLotRepository`: Parking lot configuration
- `GateRepository`: Gate information
- `PaymentRepository`: Payment records

## 🎯 Design Patterns Used

1. **Strategy Pattern**: For slot assignment and pricing strategies
2. **Repository Pattern**: For data access abstraction
3. **DTO Pattern**: For data transfer between layers
4. **Service Layer Pattern**: For business logic encapsulation
5. **Controller Pattern**: For request handling

## 🔧 Configuration

### Vehicle Types Supported
- `TWO_WHEELER`: Motorcycles, scooters
- `FOUR_WHEELER`: Cars, SUVs
- `LARGE_VEHICLE`: Trucks, buses

### Payment Types
- `CASH`: Cash payment
- `CARD`: Card payment
- `UPI`: Digital payment

### Gate Types
- `ENTRY`: Vehicle entry gates
- `EXIT`: Vehicle exit gates

## 🚨 Exception Handling

The system includes comprehensive exception handling:
- `InvalidUserDetailsException`: Invalid user information
- `InvalidVehicleDetailsException`: Invalid vehicle data
- `InvalidGateDetailsException`: Gate-related errors
- `InvalidTokenException`: Token validation failures
- `InvalidParkingLotException`: Parking lot operation errors

## 📝 Usage Example

```java
// Issue a token
IssueTokenRequestDTO request = new IssueTokenRequestDTO(
    "John Doe", 1234567890L, "123 Main St", 
    "ABC123", VehicleType.FOUR_WHEELER, 1L
);
IssueTokenResponseDTO response = tokenController.issueToken(request);

// Generate bill
GenerateBillRequestDTO billRequest = new GenerateBillRequestDTO(
    1L, response.getToken().getId(), PaymentType.CARD
);
GenerateBillResponseDTO billResponse = billController.generateBill(billRequest);
```

## 🔍 Key Classes Overview

### TokenService
- Validates user and vehicle details
- Assigns parking slots using strategies
- Generates and manages tokens

### BillService
- Calculates parking duration and fees
- Processes payments
- Generates bills and releases slots

### ParkingLotService
- Manages parking lot capacity
- Handles slot allocation and release
- Maintains parking lot state

## 🛠️ Extending the System

### Adding New Slot Assignment Strategy
1. Implement `SlotAssignmentStrategy` interface
2. Add strategy to service initialization
3. Configure strategy selection logic

### Adding New Pricing Strategy
1. Implement `PricingStrategy` interface
2. Update `PaymentService` configuration
3. Add pricing rules and calculations

## 📊 Current Limitations

- In-memory storage (data lost on restart)
- Console-based interface only
- Single parking lot support
- Basic pricing model
- No persistence layer

## 🔮 Future Enhancements

- Database integration for persistence
- REST API endpoints
- Web-based user interface
- Advanced pricing strategies (time-based, demand-based)
- Reservation system
- Real-time slot availability
- Integration with payment gateways
- Mobile application support
- Analytics and reporting

## 🤝 Contributing

1. Follow existing code style and patterns
2. Add appropriate exception handling
3. Include validation for all inputs
4. Update documentation for new features
5. Ensure thread safety for concurrent operations

## 📄 License

This project is for educational purposes and system design practice.
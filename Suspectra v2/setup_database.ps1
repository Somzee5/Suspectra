# PowerShell script to set up the database
# This script will create the database structure and insert your credentials

Write-Host "Setting up ThirdEye v2 database..." -ForegroundColor Green

# Check if SQLite is available
try {
    $sqliteVersion = sqlite3 --version
    Write-Host "SQLite found: $sqliteVersion" -ForegroundColor Green
} catch {
    Write-Host "SQLite not found in PATH. Please install SQLite Browser and run the setup_database.sql manually." -ForegroundColor Yellow
    Write-Host "1. Open SQLite Browser" -ForegroundColor Cyan
    Write-Host "2. Open login.sqlite file" -ForegroundColor Cyan
    Write-Host "3. Run the SQL commands from setup_database.sql" -ForegroundColor Cyan
    exit 1
}

# Create the database structure
$sqlCommands = @"
CREATE TABLE IF NOT EXISTS login_data (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);

INSERT OR REPLACE INTO login_data (email, password) VALUES ('akash29595@gmail.com', 'imakashsahu');

SELECT * FROM login_data;
"@

# Execute SQL commands
$sqlCommands | sqlite3 login.sqlite

Write-Host "Database setup completed!" -ForegroundColor Green
Write-Host "Login credentials:" -ForegroundColor Cyan
Write-Host "Email: sohampatilsp55@gmail.com" -ForegroundColor White
Write-Host "Password: test123" -ForegroundColor White
Write-Host ""
Write-Host "You can now run the application!" -ForegroundColor Green

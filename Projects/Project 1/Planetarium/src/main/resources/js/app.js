const state = {
    userId: sessionStorage.getItem("userId"),
    username: sessionStorage.getItem("user"),
    selectedType: "moon"
};

const elements = {};

document.addEventListener("DOMContentLoaded", initializeApp);

function initializeApp() {
    cacheSharedElements();

    setBackgroundImage("/background");

    const page = getCurrentPage();

    if (page === "login") {
        initializeLoginPage();

        return;
    }

    if (page === "create") {
        initializeCreateAccountPage();

        return;
    }

    if (page === "home") {
        initializeHomePage();

        return;
    }
}

function getCurrentPage() {
    if (document.body.dataset.page) {
        return document.body.dataset.page;
    }

    if (document.getElementById("login-form")) {
        return "login";
    }

    if (document.getElementById("create-account-form")) {
        return "create";
    }

    if (document.getElementById("celestial-table-body")) {
        return "home";
    }

    return "unknown";
}

function cacheSharedElements() {
    elements.toast = document.getElementById("app-toast");
    elements.toastTitle = document.getElementById("toast-title");
    elements.toastMessage = document.getElementById("toast-message");
    elements.logoutButton = document.getElementById("logout-button");
}

function initializeLoginPage() {
    elements.loginForm = document.getElementById("login-form");
    elements.loginUsernameInput = document.getElementById("login-username-input");
    elements.loginPasswordInput = document.getElementById("login-password-input");

    if (elements.loginForm) {
        elements.loginForm.addEventListener("submit", handleLoginSubmit);
    }

    registerOptionalLogoutButton();
}

function initializeCreateAccountPage() {
    elements.createAccountForm = document.getElementById("create-account-form");
    elements.createUsernameInput = document.getElementById("create-username-input");
    elements.createPasswordInput = document.getElementById("create-password-input");

    if (elements.createAccountForm) {
        elements.createAccountForm.addEventListener("submit", handleCreateAccountSubmit);
    }

    registerOptionalLogoutButton();
}

function initializeHomePage() {
    cacheHomeElements();
    registerHomeEventListeners();
    setGreeting();
    createMoonInputs();
    populateCelestialTable();
}

function cacheHomeElements() {
    elements.greeting = document.getElementById("greeting");
    elements.locationSelect = document.getElementById("location-select");
    elements.inputContainer = document.getElementById("input-container");
    elements.createForm = document.getElementById("create-form");
    elements.deleteInput = document.getElementById("delete-input");
    elements.deleteButton = document.getElementById("delete-button");
    elements.refreshButton = document.getElementById("refresh-button");
    elements.tableBody = document.getElementById("celestial-table-body");
    elements.planetCount = document.getElementById("planet-count");
    elements.moonCount = document.getElementById("moon-count");
}

function registerHomeEventListeners() {
    if (elements.locationSelect) {
        elements.locationSelect.addEventListener("change", handleTypeChange);
    }

    if (elements.deleteButton) {
        elements.deleteButton.addEventListener("click", deleteCelestial);
    }

    if (elements.refreshButton) {
        elements.refreshButton.addEventListener("click", populateCelestialTable);
    }

    registerOptionalLogoutButton();
}

function registerOptionalLogoutButton() {
    if (elements.logoutButton) {
        elements.logoutButton.addEventListener("click", logout);
    }
}

async function handleLoginSubmit(event) {
    event.preventDefault();

    const username = elements.loginUsernameInput.value.trim();
    const password = elements.loginPasswordInput.value;

    if (!username || !password) {
        showToast("Missing credentials", "Please enter your username and password.");

        return;
    }

    try {
        const response = await fetch("/login", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({
                username,
                password
            })
        });

        if (response.status === 200) {
            const user = await response.json();

            sessionStorage.setItem("user", user.username);
            sessionStorage.setItem("userId", user.id);

            window.location.replace("/planetarium");

            return;
        }

        showToast("Login failed", "Invalid credentials.");
    } catch (error) {
        console.error(error);
        showToast("Login failed", "Unable to contact the server.");
    }
}

async function handleCreateAccountSubmit(event) {
    event.preventDefault();

    const username = elements.createUsernameInput.value.trim();
    const password = elements.createPasswordInput.value;

    if (!isValidUsername(username)) {
        showToast("Invalid username", "Username must be 5-30 characters, start with a letter, and only use letters, numbers, underscores, or hyphens.");

        return;
    }

    if (!isValidPassword(password)) {
        showToast("Invalid password", "Password must be 5-30 characters and include at least one lowercase letter, one uppercase letter, and one number.");

        return;
    }

    try {
        const response = await fetch("/register", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        });

        if (response.status === 201) {
            showToast("Account created", "Your account was created successfully. Redirecting to login...");

            setTimeout(() => {
                window.location.replace("/");
            }, 900);

            return;
        }

        showToast("Account creation failed", await getResponseMessage(response, "Unable to create account."));
    } catch (error) {
        console.error(error);
        showToast("Account creation failed", "Unable to contact the server.");
    }
}

function setGreeting() {
    const displayName = state.username || "Explorer";

    if (elements.greeting) {
        elements.greeting.textContent = `Welcome to the Planetarium, ${displayName}`;
    }
}

function handleTypeChange(event) {
    state.selectedType = event.target.value;
    elements.inputContainer.innerHTML = "";

    if (state.selectedType === "planet") {
        createPlanetInputs();

        return;
    }

    createMoonInputs();
}

async function populateCelestialTable() {
    setLoadingRow();

    try {
        const [planets, moons] = await Promise.all([
            fetchPlanets(),
            fetchMoons()
        ]);

        renderCelestialTable(planets, moons);
        updateCounts(planets.length, moons.length);
    } catch (error) {
        console.error(error);
        setMessageRow("Unable to load celestial bodies.");
        showToast("Error", "Unable to load celestial bodies.");
    }
}

async function fetchPlanets() {
    const response = await fetch(`/planetarium/user/${state.userId}/planet`, {
        credentials: "include"
    });

    if (!response.ok) {
        return [];
    }

    return response.json();
}

async function fetchMoons() {
    const response = await fetch(`/planetarium/user/${state.userId}/moon`, {
        credentials: "include"
    });

    if (!response.ok) {
        return [];
    }

    return response.json();
}

function renderCelestialTable(planets, moons) {
    elements.tableBody.innerHTML = "";

    if (planets.length === 0 && moons.length === 0) {
        setMessageRow("No celestial bodies found. Create one to get started.");

        return;
    }

    planets.forEach(planet => {
        const row = createCelestialRow({
            type: "planet",
            id: planet.planetId,
            name: planet.planetName,
            ownerId: planet.ownerId,
            imageData: planet.imageData
        });

        elements.tableBody.appendChild(row);
    });

    moons.forEach(moon => {
        const row = createCelestialRow({
            type: "moon",
            id: moon.moonId,
            name: moon.moonName,
            ownerId: moon.ownerId,
            imageData: moon.imageData
        });

        elements.tableBody.appendChild(row);
    });
}

function createCelestialRow(celestialBody) {
    const row = document.createElement("tr");

    row.appendChild(createTextCell(capitalize(celestialBody.type)));
    row.appendChild(createTextCell(celestialBody.id));
    row.appendChild(createTextCell(celestialBody.name));
    row.appendChild(createTextCell(celestialBody.ownerId));
    row.appendChild(createImageCell(celestialBody.imageData, celestialBody.name));

    return row;
}

function createTextCell(value) {
    const cell = document.createElement("td");
    cell.textContent = value;

    return cell;
}

function createImageCell(imageData, altText) {
    const cell = document.createElement("td");

    if (!imageData) {
        const badge = document.createElement("span");
        badge.className = "badge text-bg-secondary";
        badge.textContent = "No image";

        cell.appendChild(badge);

        return cell;
    }

    const image = document.createElement("img");
    image.className = "celestial-image rounded border border-secondary";
    image.src = `data:image/jpeg;base64,${imageData}`;
    image.alt = altText;

    cell.appendChild(image);

    return cell;
}

function setLoadingRow() {
    setMessageRow("Loading celestial bodies...");
}

function setMessageRow(message) {
    if (!elements.tableBody) {
        return;
    }

    elements.tableBody.innerHTML = "";

    const row = document.createElement("tr");
    const cell = document.createElement("td");

    cell.colSpan = 5;
    cell.className = "text-center text-secondary py-4";
    cell.textContent = message;

    row.appendChild(cell);
    elements.tableBody.appendChild(row);
}

function updateCounts(planetCount, moonCount) {
    if (elements.planetCount) {
        elements.planetCount.textContent = planetCount;
    }

    if (elements.moonCount) {
        elements.moonCount.textContent = moonCount;
    }
}

function createPlanetInputs() {
    elements.inputContainer.innerHTML = `
		<div class="mb-3">
			<label for="planet-name-input" class="form-label">Planet Name</label>
			<input type="text" id="planet-name-input" class="form-control" placeholder="Enter planet name" maxlength="30" required>
		</div>

		<div class="mb-3">
			<label for="planet-image-input" class="form-label">Planet Image</label>
			<input type="file" id="planet-image-input" class="form-control" accept="image/jpeg,image/png">
		</div>

		<button class="btn btn-primary w-100" type="button" id="create-planet-button">
			<i class="bi bi-plus-circle me-1"></i>
			Create Planet
		</button>
	`;

    document.getElementById("create-planet-button").addEventListener("click", createPlanet);
}

function createMoonInputs() {
    elements.inputContainer.innerHTML = `
		<div class="mb-3">
			<label for="moon-name-input" class="form-label">Moon Name</label>
			<input type="text" id="moon-name-input" class="form-control" placeholder="Enter moon name" maxlength="30" required>
		</div>

		<div class="mb-3">
			<label for="orbited-planet-input" class="form-label">Orbited Planet ID</label>
			<input type="number" id="orbited-planet-input" class="form-control" placeholder="Enter planet ID" required>
		</div>

		<div class="mb-3">
			<label for="moon-image-input" class="form-label">Moon Image</label>
			<input type="file" id="moon-image-input" class="form-control" accept="image/jpeg,image/png">
		</div>

		<button class="btn btn-primary w-100" type="button" id="create-moon-button">
			<i class="bi bi-plus-circle me-1"></i>
			Create Moon
		</button>
	`;

    document.getElementById("create-moon-button").addEventListener("click", createMoon);
}

async function createPlanet() {
    const planetName = document.getElementById("planet-name-input").value.trim();
    const planetImageInput = document.getElementById("planet-image-input");

    if (!isValidCelestialName(planetName)) {
        showToast("Invalid planet name", "Planet names must be 1-30 valid characters.");

        return;
    }

    try {
        const imageData = await getOptionalBase64Image(planetImageInput);

        const body = {
            planetName,
            ownerId: Number(state.userId)
        };

        if (imageData) {
            body.imageData = imageData;
        }

        const response = await fetch(`/planetarium/user/${state.userId}/planet`, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body),
            credentials: "include"
        });

        if (response.status === 201) {
            showToast("Planet created", `${planetName} was added successfully.`);
            elements.createForm.reset();
            createPlanetInputs();
            await populateCelestialTable();

            return;
        }

        showToast("Unable to create planet", await getResponseMessage(response, "Invalid planet name."));
    } catch (error) {
        console.error(error);
        showToast("Unable to create planet", error.message);
    }
}

async function createMoon() {
    const moonName = document.getElementById("moon-name-input").value.trim();
    const orbitedPlanetId = document.getElementById("orbited-planet-input").value.trim();
    const moonImageInput = document.getElementById("moon-image-input");

    if (!isValidCelestialName(moonName)) {
        showToast("Invalid moon name", "Moon names must be 1-30 valid characters.");

        return;
    }

    if (!isValidPlanetId(orbitedPlanetId)) {
        showToast("Invalid planet ID", "Choose the ID of one of your existing planets.");

        return;
    }

    try {
        const imageData = await getOptionalBase64Image(moonImageInput);

        const body = {
            moonName,
            ownerId: Number(orbitedPlanetId)
        };

        if (imageData) {
            body.imageData = imageData;
        }

        const response = await fetch(`/planetarium/planet/${orbitedPlanetId}/moon`, {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body),
            credentials: "include"
        });

        if (response.status === 201) {
            showToast("Moon created", `${moonName} was added successfully.`);
            elements.createForm.reset();
            createMoonInputs();
            await populateCelestialTable();

            return;
        }

        showToast("Unable to create moon", await getResponseMessage(response, "Invalid moon name."));
    } catch (error) {
        console.error(error);
        showToast("Unable to create moon", error.message);
    }
}

async function deleteCelestial() {
    const identifier = elements.deleteInput.value.trim();
    const selectedType = elements.locationSelect.value;

    if (!identifier) {
        showToast("Missing name", "Enter the name of the planet or moon to delete.");

        return;
    }

    const url = selectedType === "moon" ? `/planetarium/moon/${encodeURIComponent(identifier)}` : `/planetarium/user/${state.userId}/planet/${encodeURIComponent(identifier)}`;

    try {
        const response = await fetch(url, {
            method: "DELETE",
            credentials: "include"
        });

        if (response.status === 204) {
            showToast("Deleted", `${capitalize(selectedType)} deleted successfully.`);
            elements.deleteInput.value = "";
            await populateCelestialTable();

            return;
        }

        showToast("Delete failed", `Invalid ${selectedType} name.`);
    } catch (error) {
        console.error(error);
        showToast("Delete failed", "Unable to contact the server.");
    }
}

async function logout() {
    try {
        const response = await fetch("/logout", {
            method: "POST",
            credentials: "include"
        });

        if (response.status === 401 || response.ok) {
            clearSessionAndRedirectToLogin();

            return;
        }

        showToast("Logout failed", "Something went wrong. Please try again.");
    } catch (error) {
        console.error(error);
        clearSessionAndRedirectToLogin();
    }
}

function clearSessionAndRedirectToLogin() {
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("userId");
    window.location.replace("/");
}

async function getOptionalBase64Image(inputElement) {
    if (!inputElement || inputElement.files.length === 0) {
        return null;
    }

    return convertToBase64(inputElement.files[0]);
}

function convertToBase64(file) {
    return new Promise((resolve, reject) => {
        const allowedTypes = ["image/jpeg", "image/png"];

        if (!allowedTypes.includes(file.type)) {
            reject(new Error("Invalid file type. Please choose a JPEG or PNG image."));

            return;
        }

        const reader = new FileReader();

        reader.onload = () => {
            const base64String = reader.result.split(",")[1];
            resolve(base64String);
        };

        reader.onerror = () => {
            reject(new Error("Failed to read file."));
        };

        reader.readAsDataURL(file);
    });
}

async function setBackgroundImage(url) {
    try {
        const response = await fetch(url);

        if (!response.ok) {
            return;
        }

        const imageData = await response.text();

        document.body.style.backgroundImage = "linear-gradient(rgba(5, 8, 20, 0.78), rgba(5, 8, 20, 0.92)), url(data:image/jpg;base64,${imageData})";
    } catch (error) {
        console.error(error);
    }
}

function isValidPlanetId(planetId) {
    if (!planetId || !elements.tableBody) {
        return false;
    }

    const rows = elements.tableBody.querySelectorAll("tr");

    for (const row of rows) {
        const cells = row.querySelectorAll("td");

        if (cells.length < 2) {
            continue;
        }

        const type = cells[0].textContent.toLowerCase();
        const id = cells[1].textContent;

        if (type === "planet" && id === planetId) {
            return true;
        }
    }

    return false;
}

function isValidCelestialName(name) {
    const regex = /^[a-zA-Z0-9\s_-]{1,30}$/;

    return regex.test(name);
}

function isValidUsername(username) {
    const regex = /^[a-zA-Z][a-zA-Z0-9_-]{4,29}$/;

    return regex.test(username);
}

function isValidPassword(password) {
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z0-9_-]{5,30}$/;

    return regex.test(password);
}

async function getResponseMessage(response, fallbackMessage) {
    const message = await response.text();

    return message || fallbackMessage;
}

function showToast(title, message) {
    if (!elements.toast || !elements.toastTitle || !elements.toastMessage || typeof bootstrap === "undefined") {
        alert(message);

        return;
    }

    elements.toastTitle.textContent = title;
    elements.toastMessage.textContent = message;

    const toast = bootstrap.Toast.getOrCreateInstance(elements.toast);
    toast.show();
}

function capitalize(value) {
    if (!value) {
        return "";
    }

    return value.charAt(0).toUpperCase() + value.slice(1);
}
# 

# 📱 INFINI.TO - Technical Documentation

Welcome to the technical documentation for the development of the mobile application dedicated to [planetarioditorino.it](https://planetarioditorino.it/) a museum in Turin. This document contains technical, design, and organizational information required to understand and evaluate the project.



<p align="center">
  <img src="https://www.turismoitalianews.it/images/stories/loghi/TorinoPlanetarioLogo.jpg" alt="Logo" />
</p>




# Indice

- [Come iniziare](#🚀-come-iniziare)
- [SiteMap](#sitemap)
- [PaletteColori](#🎨-color-palette-material-design-3-roles)


---

# 1. 🎯 Introduction

**Museum name:** Infini.to – Planetarium of Turin, Museum of Astronomy and Space

**App objectives:**
The application is designed to:
- Make museum information more accessible (exhibitions, events, opening hours, etc.)
- Improve user engagement and retention through a polished UX and engaging content
- Facilitate the purchase of tickets in an intuitive way
- (Optional) Integrate **gamification** elements to engage visitors

**Target audience:** Museum enthusiasts, families, students, and casual visitors

## 🚀 Come iniziare

### Clona il repository:
   ```bash
   git clone https://github.com/Sebastiano-ITS/Repo-App-infini.to.git
   ```

## 🛠️ Tecnologie utilizzate

- **Linguaggio:** Kotlin
- **IDE:** Android Studio
- **Architettura:** MVVM
- **Librerie principali:**
  - Jetpack Components (ViewModel, Navigation)
  - Glide per il caricamento immagini



## 📂 Struttura del progetto

<pre>

├───app
│   └───src
│       ├───androidTest
│       │   └───java
│       │       └───com
│       │           └───example
│       │               └───infinito
│       ├───main
│       │   ├───java
│       │   │   └───com
│       │   │       └───example
│       │   │           └───infinito
│       │   └───res
│       │       ├───drawable
│       │       ├───layout
│       │       ├───mipmap-anydpi
│       │       ├───mipmap-hdpi
│       │       ├───mipmap-mdpi
│       │       ├───mipmap-xhdpi
│       │       ├───mipmap-xxhdpi
│       │       ├───mipmap-xxxhdpi
│       │       ├───values
│       │       ├───values-night
│       │       └───xml
│       └───test
│           └───java
│               └───com
│                   └───example
│                       └───infinito

</pre>

# 2. 🔍 Preliminary Analysis

Before designing the structure and functionality of the app, we conducted a **preliminary analysis** of both the museum’s website and other mobile applications from renowned museums.

**Sources:**
- The official website of Infini.to
- Mobile apps from major museums such as the Louvre, British Museum, MoMA, and Vatican Museums

**Goals of the analysis:**
- Identify essential content and information to include (e.g., schedules, contacts, event details)
- Evaluate the most common and appreciated features in similar apps (e.g., user profiles, ticket purchases, news updates)
- Understand navigation patterns and layout best practices

The insights gathered during this phase directly informed the sitemap, features, and overall user experience design.



# 3. 🧭 App Architecture

### Main Screens:
- **Home** – Main dashboard with featured content
- **Profile** – User profile and settings
- **Tickets** – View and purchase museum tickets
- **News** – Updates and announcements
- **Events** – List of upcoming events and detailed descriptions
- **Opening Hours & Contacts** – Museum schedule and contact information

### Navigation:
- Navigation structure will be defined later in the development process.

### Gamification:
- Not included in the current planning phase.

### Authentication:
- The decision regarding login/registration is pending and will be evaluated later.

---


# 4. 🎨 Design Document

### Moodboard
The selected moodboard was custom-made by the team and features space-themed imagery, aligning with the astronomical identity of the museum.


## 🎨 Color Palette (Material Design 3 roles)

| Nome                        | Codice     |
|-----------------------------|------------|
| Primary                     | `#3855A8`  |
| Primary Container           | `#D5E0FF`  |
| On Primary                  | `#FFFFFF`  |
| On Primary Container        | `#0C1B3F`  |
| Secondary                   | `#4E638A`  |
| Secondary Container         | `#C8D7F2`  |
| On Secondary                | `#FFFFFF`  |
| On Secondary Container      | `#1B2A40`  |
| Tertiary                   | `#FF6E2C`  |
| Tertiary Container         | `#FFD7C2`  |
| On Tertiary                | `#FFFFFF`  |
| On Tertiary Container      | `#401100`  |
| Secondary (alt)            | `#4E638A`  |
| Secondary Container (alt)  | `#C8D7F2`  |
| On Secondary (alt)         | `#FFFFFF`  |
| On Secondary Container (alt)| `#1B2A40` |
| Background                 | `#0C0E1A`  |
| On Background              | `#F2F4F8`  |
| Surface                    | `#1D1F2A`  |
| On Surface                 | `#E1E4F0`  |
| Error                      | `#FF3B3B`  |
| On Error                   | `#FFFFFF`  |
| Outline                    | `#5E6170`  |


> _Note: The secondary color is under consideration — both the muted blue (#4E638A) and vivid violet (#4E63EA) are currently being evaluated._


### Typography
- **Primary Font:** [Orbitron](https://fonts.google.com/specimen/Orbitron) (used for headers and thematic elements)
- **Secondary Font:** [Roboto](https://fonts.google.com/specimen/Roboto) (used for body text and interface elements)

### Sitemap
```
Home
├── News
│   └── News Details
├── Profile
│   ├── Settings
│   ├── Login
│   └── Favorites
├── Events
│   ├── Kids
│   └── Event Details
├── Schools
│   └── Activity Sheets
├── Tickets
│   └── Payment
└── Opening Hours & Contacts
```

> This sitemap guides the structure of the app and informs navigation and wireframe decisions.

### Components
- No reusable UI components have been defined at this stage.

---


## 👨‍💻 Autori

<div
style="display: flex;
  flex-direction: row;
">

<div style="
  width: 50px;
  height: 50px;
  background-image: url('https://github.com/AlbusITS.png');
  background-size: cover;
  background-position: center;
  border-radius: 20%;
">
</div>

<div style="
  width: 50px;
  height: 50px;
  background-image: url('https://github.com/AleNino1210.png');
  background-size: cover;
  background-position: center;
  border-radius: 20%;
">
</div>

<div style="
  width: 50px;
  height: 50px;
  background-image: url('https://github.com/itsvachun.png');
  background-size: cover;
  background-position: center;
  border-radius: 20%;
">
</div>

<div style="
  width: 50px;
  height: 50px;
  background-image: url('https://github.com/Sebastiano-ITS.png');
  background-size: cover;
  background-position: center;
  border-radius: 20%;
">
</div>

<div style="
  width: 50px;
  height: 50px;
  background-image: url('https://github.com/edo0204.png');
  background-size: cover;
  background-position: center;
  border-radius: 20%;
">
</div>

</div>



---
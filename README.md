<div align="center">

# Maharti | مهارتي
**A peer-to-peer skill exchange application for university students**

CSC 402 — Mobile Application Development · Imam Abdulrahman Bin Faisal University

**Project status:** In development · **Platform:** Android · **UI:** Kotlin / Jetpack Compose

</div>

## Project overview

Maharti helps university students exchange skills directly: a student can offer a skill they know in return for learning another. The intended journey includes discovering students and skills, reviewing profiles, sending exchange requests, scheduling accepted exchanges, and managing sessions.

The application is being developed incrementally as part of CSC 402. Descriptions of planned features do not imply that they have already been implemented.

## Repository navigation

| Location | Purpose | How to use it |
| --- | --- | --- |
| [`main` branch](https://github.com/maisaZI/CSC-402-Maharti/tree/main) | Central project documentation and milestone history | Start here for the project overview, team, and earlier deliverables |
| [`docs/`](docs/) | Shared documentation | Browse design, architecture, wireframes, database, and earlier milestone submissions |
| [`docs/milestone-2/`](docs/milestone-2/) | Milestone 2 records | Stage 1 and Stage 2 materials |
| [`docs/database/`](docs/database/) | Database documentation | ERD and relational schema artifacts |
| [`app` branch](https://github.com/maisaZI/CSC-402-Maharti/tree/app) | Android implementation and Milestone 3 Stage 2 workspace | Switch to this branch for member submissions, integrated code, and the report |
| [Issues](https://github.com/maisaZI/CSC-402-Maharti/issues) | Assigned tasks | Find responsibilities and track progress |
| [Pull requests](https://github.com/maisaZI/CSC-402-Maharti/pulls) | Review and integration | Review changes before merging |
| [TEAM.md](TEAM.md) | Team roster | See members and earlier recorded responsibilities |
| [CONTRIBUTING.md](CONTRIBUTING.md) | Historical contribution instructions | Describes the earlier Milestone 2 workflow; use the `app` branch guide for current work |

### Android implementation workspace (`app` branch)

```text
milestone-3-stage-2/
├── individual-work/
│   ├── Maisa/
│   ├── Samar/
│   ├── Haya/
│   ├── Lyan/
│   ├── Lena/
│   ├── Monera/
│   └── Reyouf/
└── report/
AndroidProject/
```

- **`individual-work/<member>/`**: each member's independently authored screen files and related notes, committed from her own GitHub account. These are contribution records, **not** the runnable application.
- **`report/`**: the completed Milestone 3 Stage 2 submission PDF when ready.
- **`AndroidProject/`**: the **single integrated Android Studio project**, to be added after the team establishes and tests it. Future functionality should extend this same project rather than creating duplicate app copies.

The initial folders are scaffolding only. A runnable Android project and final PDF have **not** been uploaded yet.

## Contribution and integration workflow

1. Open the **`app` branch** and find your assigned GitHub Issue.
2. Upload your own work to `milestone-3-stage-2/individual-work/<your-name>/` with a descriptive commit message.
3. Coordinate integration into the single project under `AndroidProject/`. The integrated code belongs in the Android project's normal `app/src/main/java/...` package structure.
4. Review navigation, UI consistency, build, and run behavior in Android Studio. Do not mark untested code as complete.
5. Commit the tested integrated changes to the **`app` branch**. Record screen files, route functions, and contributions in the report.
6. For the milestone submission, ensure the required `milestone-3` tag points to the approved final commit and that the instructor can access the tested project. If a submission must be available from `main`, merge the approved implementation there first.

Each member should commit her own work. Do not attribute another person's contribution to yourself. The course requires a buildable project, connected navigation, and accurate AI-use disclosure in the Stage 2 report.

## Planned application screens

Login / Sign Up · Forgot Password · Home · Search & Filters · Student Profile · Exchange Request · Session Scheduling · My Exchanges · Profile

**Intended user flow:** Search for a skill → view a student profile → send an exchange request → accept → schedule a session → manage the exchange.

## Design and technology

- **Design:** [Maharti Figma prototype](https://www.figma.com/design/C7kJyspozVnCzA4Zkg1uwp/Maharti-%E2%80%93-Purple---Gold-Prototype)
- **Development:** Android Studio, Kotlin, Jetpack Compose, Material 3
- **Collaboration:** GitHub Issues, commits, and pull requests
- **Documentation:** [docs/README.md](docs/README.md)

## Academic context

**Course:** CSC 402 — Mobile Application Development  
**Project:** Maharti (مهارتي)  
**Current implementation phase:** Milestone 3, Stage 2 — screen layouts and navigation

For team membership and contributions, see [TEAM.md](TEAM.md) and the milestone-specific report.

---

<div align="center"><sub>Maharti — Learn a skill. Share a skill. Grow together.</sub></div>

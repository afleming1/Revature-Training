# Maven

## Table of Contents:
- [1. Introduction to Maven](#1-introduction-to-maven)
- [2. Project Coordinates](#2-project-coordinates)
    - [2.1 Project Coordinate Anatomy](#21-project-coordinate-anatomy)
    - [2.2 SNAPSHOT vs Release Versions](#22-snapshot-vs-release-versions)
    - [2.3 Standard Project Layout](#23-standard-project-layout)
- [3. Project Object Model (POM)](#3-project-object-model-pom)
    - [3.1 Structure of `pom.xml`](#31-structure-of-pomxml)
- [4. Maven Repositories](#4-maven-repositories)
    - [4.1 Three Repository Types](#41-three-repository-types)
    - [4.2 How Dependency Resolution Works](#42-how-dependency-resolution-works)
- [5. Maven Lifecycles](#5-maven-lifecycles)
    - [5.1 Phases vs Goals](#51-phases-vs-goals)
    - [5.2 Default Lifecycle](#52-default-lifecycle)
    - [5.3 Clean and Site Lifecycles](#53-clean-and-site-lifecycles)

## 1. Introduction to Maven
**Maven** is a build automation tool for Java. Maven centralized how projects are built and made into a declarative configuration file, `pom.xml`. The command for almost all Maven commands contains `mvn`.

| Feature | What it Solves |
| :--- | :--- |
| **Convention over configuration** | Provides a standard project layout, so any developer recognized the structure of any Maven project |
| **Dependency management** | Automatically downloads libraries your code needs, including their transitive dependencies |
| **Standardized lifecycle** | Every project follows the same ordered build phases, replacing bespoke scripts |
| **Reproducible builds** | The same POM and version produce the same result on any machine, everytime |
| **Plugin ecosystem** | Compilers, test runners, and pacakgers are just plugins that bind to lifecycle phases |

[&uarr; Back to Table of Contents](#table-of-contents)

## 2. Project Coordinates
**Project Coordinates** uniquely identify one artifact (a project, a library, or any packaged unit of code) inside the Maven universe. Every artifact Maven can build or download is addressable by this identity.

### 2.1 Project Coordinate Anatomy
A full set of coordinates is a combination of four elements. These are required, and the fourth has a default value.

| Element | Example | Purpose |
| :--- | :--- | :--- |
| **`groupId`** | `com.revature` | Reverse-domain namespace that groups projects (usually your company or organization's domain) |
| **`artifactId`** | `espresso-app` | Name of this specific artifact within the group |
| **`version`** | `1.0.0` | Release iteration of the artifact. Versionless artifacts are not allowed |
|  **`packaging`** | `jar` | How the artifact is packaged (`jar`, `war`, `maven-plugin`). Defaults to `jar` |

These form the artifacts coordinates, usually written as a three-part string with colons: `com.revature:espresso-app:1.0.0`

> [!IMPORTANT]
> The `groupId` + `artifactId` + `version` combination must be **globally unique**. Two different artifacts cannot share teh same full coordinate, since that is the exact key Maven uses to find and cache them.

### 2.2 SNAPSHOT vs Release Versions
Version tells Maven not just *which* artifact, but *how final* it is.

- **Release version** (`1.0.0`) - Stable, published version. Once released, it is normally **immutable** - a given coordinate return the same content.
- **SNAPSHOT vesion** (`1.0.0-SNAPSHOT`) - Development build. The `-SNAPSHOT` suffix tells Maven this artifact is still changing, so it will **re-download** it from the repository on each build rather than trusting a cached copy.

| Property | Release | SNAPSHOT |
| :--- | :--- | :--- |
| **Stability** | Frozen, Published | Still under development |
| **Caching** | Fetched Once, Cached | Re-fetched on each build |
| **Use Case** | Releases to Consumers | Team-internal development |
| **Example** | `1.0.0` | `1.0.0-SNAPSHOT` |

### 2.3 Standard Project Layout
Part of Maven's convention over configuration is a fixed directory structure that Maven assumes unless told otherwise.

- `src/main/java` - Production Source Code
- `src/test/java` - Test Source Code

Output from the build is written to a `target/` directory, which holds compiled classes, test reports, and the final packaged artifact.

[&uarr; Back to Table of Contents](#table-of-contents)

## 3. Project Object Model (POM)
The **Project Object Model (POM)** is the fundamental unit of work in Maven. It is an XML file named `pom.xml`, located in the root of every project, that describes everything Maven needs to know about that project: its coordinates, its dependencies, its build configuration, and its relationships to parent objects.

### 3.1 Structure of `pom.xml`
Minimal POM only needs the coordinates from [Section 2](#2-project-coordinates), wrapped in the Maven project namespace.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.revature</groupId>
    <artifactId>espresso-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
</project>
```

The full POM adds a range of configuration blocks.
| Element | Purpose |
| :--- | :--- |
| **`modelVersion`** | POM version Maven parses (`4.0.0`). Always present |
| **Coordinates** | `groupId`, `artifactId`, `version`, `packaging` from [Section 2](#2-project-coordinates) |
| **`properties`** | Reusable key-value settings |
| **`dependencies`** | External libraries the project needs, listed by their coordinates + scope |
| **`build`** | Plugins, their configurations, and output settings |
| **`parent`** | Coordinates of a parent POM this one inherits from |
| **`dependencyManagement`** | Central place to pin versions so child projects don't repeat them |

Dependencies are declared by pointing at the dependency's own coordinates and scope (`compile` / `test`).

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

The `<scope>test</scope>` indicates this dependency is available during testing only and is not packaged into the final artifact.

[&uarr; Back to Table of Contents](#table-of-contents)

## 4. Maven Repositories
A **repository** is a storage location for Maven artifacts (JARs, POMs, plugins). When your POM declares a dependency, Maven must go find that artifact somewhere.

### 4.1 Three Repository Types
Maven draws from up to three repositories, each with a distinct role.

| Repository | Location | Role |
| :--- | :--- | :--- |
| **Local Repository** | Your Machine (`~/.m2/repository`) | Cache of everything Maven has already downloaded or installed. Always checked first |
| **Central Repository** | [`repo.maven.apache.org`](https://mvnrepository.com/) | Maven's default public repository, hosting most open-source libraries. Check when the local cache misses |
| **Remote Repository** | Custom URL (Corporate Nexus / Artifact Server) | Private repository for internal or custom artifacts. Checked when artifact is not in central |

### 4.2 How Dependency Resolution Works
When a build references a dependency, Maven resolves it through a fixed lookup order.

//

1. Maven checks the **local repository** for the artifact's exact coordinates
2. If absent, it queries the configured **central/remote** repositories
3. The downloaded artifact is **cached to the local repository** for future builds
4. If no repository has it, the build **fails** with a dependency-not-found error

> [!WARNING]
> A SNAPSHOT dependency is intentionally *not* trusted from the local cache — Maven re-checks the remote repository on each build so you always get the latest development copy. This is what makes SNAPSHOTs useful, and also why they slow builds down.

> [!NOTE]
> Repositories are not the same as build output. The local `~/.m2/repository` cache and the per-project `target/` directory store entirely different things: the former holds downloaded libraries, the latter holds the current project's compiled results.

[&uarr; Back to Table of Contents](#table-of-contents)

## 5. Maven Lifecycles
A **lifecycle** is the ordered sequence of build stages (phases) that Maven runs to build and deploy a project. You never tell Maven *how* to compiled or package; you name a point in the lifecycle and Maven runs every phase up to and including it.

### 5.1 Phases vs Goals
- **Phase** - Stage in the lifecycle (`compile`, `test`). Phases are just markers in a sequence.
- **Goal** - Specific task that actually does work, supplied by a plugin and *bound* to a phase.

> [!IMPORTANT]
> Phase - *When* something happens in the build sequence. Goal - *What* happens. Running `mvn package` triggers `package` phase plus every phase before it, but the real work is done by the goals bound to those phases.

### 5.2 Default Lifecycle
The **default lifecycle** is the main build sequence and by far the most important.

| Phase | What Happens |
| :--- | :--- |
| **`validate`** | Checks the project is correct and all needed info is available |
| **`compile`** | Compiles the source code in `src/main/java` |
| **`test`** | Runs tests against the compiled source using a test framework |
| **`package`** | Packages the compiled code into its distributable form (JAR / WAR) |
| **`verify`** | Runs any extra checks on the package (integration tests) |
| **`install`** | Copies the package into the local repository for use by other projects |
| **`deploy`** | Copies the package to a remote repository for external use |

Because phases are cumulative, a single command executes a whole chain.

- `mvn compile` &rarr; `validate` + `compile`
- `mvn test` &rarr; `validate` + `compile` + `test`
- `mvn package` &rarr; `validate` + `compile` + `test` + `package`

> [!TIP]
> This cumulative behavior is a huge time-saver: `mvn package` automatically runs `validate`, `compile` and `test` first, so you never have to chain commands yourself.

### 5.3 Clean and Site Lifecycles
Two shorter lifecycles complement the default one.

| Lifecycle | Purpose |
| :--- | :--- |
| **`clean`** | Removes the `target/` directory and any prior build output |
| **`site`** | Generates project documentation for a website report |

`clean` is frequently combined with the default lifecycle in a single invocation: `mvn clean install` first wipes the previous output, then runs the default lifecycle all the way through `install`. This is a common idiom for guranteeing a build starts from a truly clean state.

[&uarr; Back to Table of Contents](#table-of-contents)
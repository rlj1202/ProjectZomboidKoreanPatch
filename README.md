# ProjectZomboidKoreanPatch
Modifies ProjectZomboid source codes to be able to type korean
characters.

    ProjectZomboidKoreanPatch/
        patch/                 # patched classes
        launcher/              # PZ launcher
        installer/             # DEPRECATED class patcher

`patch` contains classes which will be patched or added actually.

`launcher` is executor to run PZ with patched or added classes.

`installer` installs patched or added classes into PZ directory directly.

Patched classes

    - zombie.core.Core

Added classes

    - redlaboratory.koreancore.KoreanCore
    - redlaboratory.koreancore.Result

## How it works
Launcher jar file contains patched and added classes and loads them
through resource stream into temp directory.
And launcher executes PZ by creating other java process and passes the
temp directory path as classpath.
Then java classloader of newly created process loads classes to be
patched or added in temp directory first. The other classes will be loaded from
PZ directory.

Launcher will reads Steam installation path through Window Registry
automatically to find Steam app library directories so that launcher
can find PZ directory.

## Used libraries
- win-registry (https://github.com/sarxos/win-registry)
- vdf-json-java (https://github.com/nosoop/vdf-json-java)

# Info
Java version: 1.7

# Contacts
e-mail: rlj1202@gmail.com

blog: http://redlaboratory.tistory.com/

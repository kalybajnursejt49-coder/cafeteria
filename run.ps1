$ErrorActionPreference = 'Stop'
$project = Split-Path -Parent $MyInvocation.MyCommand.Path
$javacCommand = Get-Command javac.exe -ErrorAction SilentlyContinue

if ($javacCommand) {
    $javac = $javacCommand.Source
} else {
    $idea = Get-ChildItem -LiteralPath "$env:ProgramFiles\JetBrains" -Directory -Filter 'IntelliJ IDEA *' -ErrorAction SilentlyContinue | Sort-Object Name -Descending | Select-Object -First 1
    if (-not $idea) {
        throw 'JDK not found. Install JDK 8+ or select a JDK in IntelliJ IDEA.'
    }
    $javac = Join-Path $idea.FullName 'jbr\bin\javac.exe'
}

if (-not (Test-Path -LiteralPath $javac)) {
    throw 'javac.exe not found. Install JDK 8+.'
}

$java = Join-Path (Split-Path -Parent $javac) 'java.exe'
$build = Join-Path $project 'build'
New-Item -ItemType Directory -Path $build -Force | Out-Null
$sources = Get-ChildItem -LiteralPath (Join-Path $project 'src\main\java') -Filter '*.java' -Recurse | Select-Object -ExpandProperty FullName
& $javac -encoding UTF-8 -d $build $sources
if ($LASTEXITCODE -ne 0) {
    throw 'Compilation failed.'
}
& $java -cp $build cafeteria.Main
if ($LASTEXITCODE -ne 0) {
    throw 'Program failed.'
}

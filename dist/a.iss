; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "shooting"
#define MyAppVersion "1.5"
#define MyAppPublisher "My Company, Inc."
#define MyAppURL "http://www.example.com/"
#define MyAppExeName "shooting.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{E9D84A86-C0E3-41A1-8E9D-D31176A514F3}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
OutputDir=C:\Users\DEEPAK\Desktop
OutputBaseFilename=birdgsme
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "I:\cp\summer\netbeans\shooting\dist\shooting.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\base.form"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\base.java"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\bird.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\bullet.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\DARK KNIGHT.au"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\DEATH NOTE.au"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\emp.au"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\empty_gun.au"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\fall.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\menu.form"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\menu.java"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\README.TXT"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\rsz_angrynew.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\shooting.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\sound.au"; DestDir: "{app}"; Flags: ignoreversion
Source: "I:\cp\summer\netbeans\shooting\dist\target.png"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent


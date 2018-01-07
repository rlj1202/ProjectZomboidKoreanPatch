// 
// Decompiled by Procyon v0.5.30
// 

package zombie.core;

import zombie.SVNRevision;
import zombie.ZomboidGlobals;
import java.net.URISyntaxException;
import zombie.ZomboidFileSystem;
import zombie.Lua.LuaHookManager;
import zombie.gameStates.ChooseGameInfo;
import zombie.characters.traits.TraitFactory;
import zombie.characters.professions.ProfessionFactory;
import zombie.characters.SurvivorFactory;
import zombie.scripting.ScriptManager;
import org.lwjgl.input.Controller;
import fmod.FMOD_DriverInfo;
import fmod.javafmod;
import zombie.core.raknet.VoiceManager;
import zombie.SoundManager;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;
import java.util.HashMap;
import zombie.iso.IsoCamera;
import org.lwjgl.util.glu.GLU;
import zombie.core.textures.TextureID;
import org.lwjgl.opengl.GLContext;
import zombie.Lua.LuaEventManager;
import zombie.core.VBO.GLVertexBufferObject;
import org.lwjgl.opengl.PixelFormat;
import zombie.core.znet.SteamUtils;
import zombie.input.GameKeyboard;
import zombie.network.GameClient;
import org.lwjgl.input.Keyboard;
import se.krka.kahlua.vm.KahluaTable;
import java.util.List;
import zombie.debug.DebugLog;
import java.lang.management.ManagementFactory;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.util.Iterator;
import org.lwjgl.opengl.DisplayMode;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.CopyOption;
import java.util.ArrayList;
import zombie.core.utils.OnceEvery;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import zombie.network.GameServer;
import zombie.input.JoypadManager;
import zombie.iso.MultiStageBuilding;
import zombie.characters.IsoPlayer;
import zombie.core.logger.ExceptionLogger;
import zombie.IndieGL;
import zombie.Lua.LuaManager;
import zombie.ui.UIManager;
import zombie.core.textures.TextureFBO;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import zombie.GameWindow;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;
import zombie.core.opengl.RenderThread;
import zombie.FrameLoader;
import java.io.IOException;
import zombie.core.textures.Texture;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import zombie.iso.Vector2;
import java.util.Map;
import zombie.core.opengl.Shader;
import zombie.ui.UITextBox2;
import java.util.LinkedList;
import zombie.core.textures.MultiTextureFBO2;
import zombie.core.textures.ColorInfo;
import java.awt.Canvas;

// XXX AddedCodesInHere: imports
import redlaboratory.koreancore.KoreanCore;
import redlaboratory.koreancore.Result;

public class Core
{
    public static final boolean bDemo = false;
    public static boolean bTutorial;
    private static boolean fakefullscreen;
    public String versionNumber;
    public String steamServerVersion;
    public static int SVN_REVISION;
    static Canvas canvas;
    static Canvas fullscreencanvas;
    public static boolean bMultithreadedRendering;
    public static boolean bDoubleSize;
    public static boolean bAltMoveMethod;
    public boolean rosewoodSpawnDone;
    private ColorInfo objectHighlitedColor;
    public static boolean AutoTest;
    public MultiTextureFBO2 OffscreenBuffer;
    public static float Zoom;
    public static boolean useLwjgl;
    public static boolean DoFiltering;
    private String saveFolder;
    public static boolean OptionZoom;
    public static boolean OptionModsEnabled;
    public static String OptionInventoryFont;
    public static String OptionMeasurementFormat;
    public static int OptionClockFormat;
    public static boolean OptionClock24Hour;
    public static boolean OptionVSync;
    public static int OptionSoundVolume;
    public static int OptionMusicVolume;
    public static int OptionAmbientVolume;
    public static int OptionMusicLibrary;
    public static boolean OptionVoiceEnable;
    public static int OptionVoiceMode;
    public static int OptionVoiceVADMode;
    public static String OptionVoiceRecordDeviceName;
    public static int OptionVoiceVolumeMic;
    public static int OptionVoiceVolumePlayers;
    public static int OptionFliesVolume;
    public static int OptionHeartVolume;
    public static int OptionReloadDifficulty;
    public static boolean OptionRackProgress;
    public static int OptionBloodDecals;
    public static boolean OptionBorderlessWindow;
    public static boolean OptionTextureCompression;
    public static boolean OptionTexture2x;
    private static String OptionZoomLevels1x;
    private static String OptionZoomLevels2x;
    public static boolean OptionEnableContentTranslations;
    private boolean showPing;
    private boolean forceSnow;
    private boolean zombieGroupSound;
    private String blinkingMoodle;
    private boolean tutorialDone;
    private String poisonousBerry;
    private String poisonousMushroom;
    private boolean doneNewSaveFolder;
    private static String difficulty;
    public static int TileScale;
    private boolean inInventory;
    private boolean isSelectingAll;
    private boolean showYourUsername;
    private ColorInfo mpTextColor;
    private boolean isAzerty;
    private String seenUpdateText;
    private boolean toggleToRun;
    private boolean celsius;
    private boolean warnMapConflict;
    private LinkedList<String> mapOrder;
    private boolean riversideDone;
    boolean bChallenge;
    public static int width;
    public static int height;
    public static int MaxJukeBoxesActive;
    public static int NumJukeBoxesActive;
    public static String GameMode;
    private static String glVersion;
    private static int glMajorVersion;
    private static Core core;
    public static boolean bDebug;
    public static boolean bHighSqualityShadows;
    public int lastHeight;
    public int lastWidth;
    public static UITextBox2 CurrentTextEntryBox;
    public static String storyDirectory;
    public static String modRootDirectory;
    public Shader RenderShader;
    private Map<String, Integer> keyMaps;
    public boolean bUseShaders;
    public int vidMem;
    public int nGraphicLevel;
    public boolean bSupportsFBO;
    public float maxZoom;
    int lviewwid;
    int lviewhei;
    public Vector2[] CircleVecs;
    ByteBuffer buffer;
    BufferedImage image;
    Texture buffertexture;
    public boolean bInFrame;
    public int version;
    public int fileversion;
    public static final String SUN_JAVA_COMMAND = "sun.java.command";
    public static boolean fullScreen;
    static boolean[] bAutoZoom;
    public static String GameMap;
    public static String GameSaveWorld;
    public static boolean SafeMode;
    public static boolean SafeModeForced;
    public static boolean SoundDisabled;
    public static boolean bIsSteam;
    public int frameStage;
    int stack;
    public static boolean bLastStand;
    public static boolean bLoadedWithMultithreaded;
    public static boolean bExiting;
    
    public Core() {
        this.versionNumber = "38.30";
        this.steamServerVersion = "1.0.0.0";
        this.rosewoodSpawnDone = false;
        this.objectHighlitedColor = new ColorInfo(0.98f, 0.56f, 0.11f, 1.0f);
        this.OffscreenBuffer = new MultiTextureFBO2();
        this.saveFolder = null;
        this.showPing = true;
        this.forceSnow = false;
        this.zombieGroupSound = true;
        this.blinkingMoodle = null;
        this.tutorialDone = false;
        this.poisonousBerry = null;
        this.poisonousMushroom = null;
        this.doneNewSaveFolder = false;
        this.inInventory = false;
        this.isSelectingAll = false;
        this.showYourUsername = true;
        this.mpTextColor = null;
        this.isAzerty = false;
        this.seenUpdateText = "";
        this.toggleToRun = false;
        this.celsius = false;
        this.warnMapConflict = false;
        this.mapOrder = null;
        this.riversideDone = false;
        this.lastHeight = 0;
        this.lastWidth = 0;
        this.keyMaps = null;
        this.bUseShaders = true;
        this.vidMem = 3;
        this.nGraphicLevel = 5;
        this.bSupportsFBO = true;
        this.maxZoom = 1.0f;
        this.lviewwid = -1;
        this.lviewhei = -1;
        this.CircleVecs = new Vector2[32];
        this.bInFrame = false;
        this.version = 1;
        this.fileversion = 7;
        this.frameStage = 0;
        this.stack = 0;
    }
    
    public boolean isMultiThread() {
        return Core.bMultithreadedRendering;
    }
    
    public void setChallenge(final boolean bChallenge) {
        this.bChallenge = bChallenge;
    }
    
    public boolean isChallenge() {
        return this.bChallenge;
    }
    
    public void setFramerate(final int index) {
        if (index == 1) {
            PerformanceSettings.LockFPS = 60;
        }
        if (index == 2) {
            PerformanceSettings.LockFPS = 45;
        }
        if (index == 3) {
            PerformanceSettings.LockFPS = 30;
        }
    }
    
    public void setMultiThread(final boolean val) {
        Core.bMultithreadedRendering = val;
        try {
            this.saveOptions();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean loadedShader() {
        return this.RenderShader != null;
    }
    
    public static int getGLMajorVersion() {
        if (Core.glMajorVersion == -1) {
            getOpenGLVersions();
        }
        return Core.glMajorVersion;
    }
    
    public boolean isDedicated() {
        return FrameLoader.bDedicated;
    }
    
    public boolean getUseShaders() {
        return this.bUseShaders;
    }
    
    public int getVidMem() {
        if (Core.SafeMode) {
            return 5;
        }
        return this.vidMem;
    }
    
    public void setVidMem(final int mem) {
        if (Core.SafeMode) {
            this.vidMem = 5;
        }
        this.vidMem = mem;
        try {
            this.saveOptions();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setUseShaders(final boolean bUse) {
        this.bUseShaders = bUse;
        try {
            this.saveOptions();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void shadersOptionChanged() {
        RenderThread.borrowContext();
        if (this.bUseShaders && !Core.SafeModeForced) {
            try {
                if (!FrameLoader.bDedicated && this.RenderShader == null) {
                    this.RenderShader = new Shader("screen");
                }
                if (this.RenderShader != null && (this.RenderShader.FragID == 0 || this.RenderShader.VertID == 0)) {
                    this.RenderShader = null;
                }
            }
            catch (Exception ex) {
                this.RenderShader = null;
            }
        }
        else if (this.RenderShader != null) {
            try {
                this.RenderShader.destroy();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.RenderShader = null;
        }
        RenderThread.returnContext();
    }
    
    public void initShaders() {
        try {
            if (!FrameLoader.bDedicated && this.RenderShader == null && !Core.SafeMode && !Core.SafeModeForced) {
                RenderThread.borrowContext();
                this.RenderShader = new Shader("screen");
                RenderThread.returnContext();
            }
            if (this.RenderShader == null || this.RenderShader.FragID == 0 || this.RenderShader.VertID == 0) {
                this.RenderShader = null;
            }
        }
        catch (Exception ex) {
            this.RenderShader = null;
            ex.printStackTrace();
        }
    }
    
    public static String getGLVersion() {
        if (Core.glVersion == null) {
            getOpenGLVersions();
        }
        return Core.glVersion;
    }
    
    public String getGameMode() {
        return Core.GameMode;
    }
    
    public static Core getInstance() {
        return Core.core;
    }
    
    public static void getOpenGLVersions() {
        Core.glVersion = GL11.glGetString(7938);
        Core.glMajorVersion = Core.glVersion.charAt(0) - '0';
    }
    
    public boolean getDebug() {
        return Core.bDebug;
    }
    
    public static void setFullScreen(final boolean bool) throws LWJGLException {
        Core.fullScreen = bool;
    }
    
    public static int[] flipPixels(final int[] imgPixels, final int imgw, final int imgh) {
        int[] flippedPixels = null;
        if (imgPixels != null) {
            flippedPixels = new int[imgw * imgh];
            for (int y = 0; y < imgh; ++y) {
                for (int x = 0; x < imgw; ++x) {
                    flippedPixels[(imgh - y - 1) * imgw + x] = imgPixels[y * imgw + x];
                }
            }
        }
        return flippedPixels;
    }
    
    public void TakeScreenshot() {
        GL11.glPixelStorei(3333, 1);
        GL11.glReadBuffer(1028);
        int width = Display.getDisplayMode().getWidth();
        int height = Display.getDisplayMode().getHeight();
        int x = 0;
        int y = 0;
        if (width > 256) {
            x = width / 2 - 128;
            width = 256;
        }
        if (height > 256) {
            y = height / 2 - 128;
            height = 256;
        }
        y += 32;
        x += 10;
        if (y < 0) {
            y = 0;
        }
        final int bpp = 3;
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
        GL11.glReadPixels(x, y, width, height, 6407, 5121, buffer);
        int[] pixels = new int[width * height];
        final File file = new File(GameWindow.getGameModeCacheDir() + File.separator + Core.GameSaveWorld + File.separator + "thumb.png");
        final String format = "png";
        for (int i = 0; i < pixels.length; ++i) {
            final int bindex = i * 3;
            pixels[i] = (0xFF000000 | (buffer.get(bindex) & 0xFF) << 16 | (buffer.get(bindex + 1) & 0xFF) << 8 | (buffer.get(bindex + 2) & 0xFF) << 0);
        }
        buffer = null;
        pixels = flipPixels(pixels, width, height);
        final BufferedImage image = new BufferedImage(width, height, 2);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        try {
            ImageIO.write(image, "png", file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Texture.forgetTexture(GameWindow.getGameModeCacheDir() + File.separator + Core.GameSaveWorld + File.separator + "thumb.png");
    }
    
    public void TakeFullScreenshot(String filename) {
        try {
            RenderThread.borrowContext();
            GL11.glPixelStorei(3333, 1);
            GL11.glReadBuffer(1028);
            final int width = Display.getDisplayMode().getWidth();
            final int height = Display.getDisplayMode().getHeight();
            final int x = 0;
            final int y = 0;
            final int bpp = 3;
            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
            GL11.glReadPixels(x, y, width, height, 6407, 5121, buffer);
            int[] pixels = new int[width * height];
            if (filename == null) {
                final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
                filename = "screenshot_" + sdf.format(Calendar.getInstance().getTime()) + ".png";
            }
            final File file = new File(GameWindow.getScreenshotDir() + File.separator + filename);
            for (int i = 0; i < pixels.length; ++i) {
                final int bindex = i * 3;
                pixels[i] = (0xFF000000 | (buffer.get(bindex) & 0xFF) << 16 | (buffer.get(bindex + 1) & 0xFF) << 8 | (buffer.get(bindex + 2) & 0xFF) << 0);
            }
            buffer = null;
            pixels = flipPixels(pixels, width, height);
            final BufferedImage image = new BufferedImage(width, height, 2);
            image.setRGB(0, 0, width, height, pixels, 0, width);
            try {
                ImageIO.write(image, "png", file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        finally {
            RenderThread.returnContext();
        }
    }
    
    public static boolean supportNPTTexture() {
        return false;
    }
    
    public boolean supportsFBO() {
        if (FrameLoader.bDedicated) {
            return false;
        }
        if (Core.SafeMode) {
            return this.OffscreenBuffer.bZoomEnabled = false;
        }
        if (!this.bSupportsFBO) {
            return false;
        }
        if (this.OffscreenBuffer.Current != null) {
            return true;
        }
        try {
            if (TextureFBO.checkFBOSupport() && this.setupMultiFBO()) {
                return true;
            }
            this.bSupportsFBO = false;
            getInstance();
            Core.SafeMode = true;
            return this.OffscreenBuffer.bZoomEnabled = false;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.bSupportsFBO = false;
            getInstance();
            Core.SafeMode = true;
            return this.OffscreenBuffer.bZoomEnabled = false;
        }
    }
    
    void sharedInit() {
        if (this.supportsFBO()) {}
    }
    
    public void MoveMethodToggle() {
        Core.bAltMoveMethod = !Core.bAltMoveMethod;
    }
    
    public void doubleSizeToggle() {
        Core.bDoubleSize = !Core.bDoubleSize;
        UIManager.resize();
    }
    
    public void EndFrameText(final int nPlayer) {
        if (LuaManager.thread.bStep) {
            return;
        }
        if (this.OffscreenBuffer.Current != null) {}
        IndieGL.glDoEndFrame();
        this.frameStage = 2;
    }
    
    public void EndFrame(final int nPlayer) {
        if (LuaManager.thread.bStep) {
            return;
        }
        if (this.OffscreenBuffer.Current != null) {
            SpriteRenderer.instance.glBuffer(0, nPlayer);
        }
        IndieGL.glDoEndFrame();
        this.frameStage = 2;
    }
    
    public void EndFrame() {
        IndieGL.glDoEndFrame();
        if (this.OffscreenBuffer.Current != null) {
            SpriteRenderer.instance.glBuffer(0, 0);
        }
    }
    
    public void CalcCircle() {
        final Vector2 Vec = new Vector2(0.0f, -1.0f);
        for (int n = 0; n < 32; ++n) {
            this.CircleVecs[n] = new Vector2(Vec.x, Vec.y);
            Vec.rotate(0.19634955f);
        }
    }
    
    public void DrawCircle(final float rad, final float x, final float y) {
    }
    
    public void EndFrameUI() {
        if (LuaManager.thread != null && LuaManager.thread.bStep) {
            SpriteRenderer.instance.clearSprites();
            SpriteRenderer.instance.numSprites = 0;
            return;
        }
        ExceptionLogger.render();
        IndieGL.glDoEndFrame();
        RenderThread.Ready();
        this.frameStage = 0;
    }
    
    public static void UnfocusActiveTextEntryBox() {
        if (Core.CurrentTextEntryBox != null) {
            Core.CurrentTextEntryBox.DoingTextEntry = false;
            if (Core.CurrentTextEntryBox.Frame != null) {
                Core.CurrentTextEntryBox.Frame.Colour = Core.CurrentTextEntryBox.StandardFrameColour;
            }
            Core.CurrentTextEntryBox = null;
        }
    }
    
    public int getOffscreenWidth(final int playerIndex) {
        if (this.OffscreenBuffer != null && this.OffscreenBuffer.Current != null) {
            return this.OffscreenBuffer.getWidth(playerIndex);
        }
        if (IsoPlayer.numPlayers > 1) {
            return this.getScreenWidth() / 2;
        }
        return this.getScreenWidth();
    }
    
    public int getOffscreenHeight(final int playerIndex) {
        if (this.OffscreenBuffer != null && this.OffscreenBuffer.Current != null) {
            return this.OffscreenBuffer.getHeight(playerIndex);
        }
        if (IsoPlayer.numPlayers > 2) {
            return this.getScreenHeight() / 2;
        }
        return this.getScreenHeight();
    }
    
    public int getOffscreenTrueWidth() {
        if (this.OffscreenBuffer == null || this.OffscreenBuffer.Current == null) {
            return this.getScreenWidth();
        }
        return this.OffscreenBuffer.getTexture(0).getWidth();
    }
    
    public int getOffscreenTrueHeight() {
        if (this.OffscreenBuffer == null || this.OffscreenBuffer.Current == null) {
            return this.getScreenHeight();
        }
        return this.OffscreenBuffer.getTexture(0).getHeight();
    }
    
    public int getScreenHeight() {
        return Core.height;
    }
    
    public int getScreenWidth() {
        return Core.width;
    }
    
    public void setResolutionAndFullScreen(final int w, final int h, final boolean fullScreen) {
        setDisplayMode(w, h, fullScreen);
        this.setScreenSize(Display.getWidth(), Display.getHeight());
    }
    
    public void setResolution(final String res) {
        final String[] bits = res.split("x");
        final int w = Integer.parseInt(bits[0].trim());
        final int h = Integer.parseInt(bits[1].trim());
        if (Core.fullScreen) {
            setDisplayMode(w, h, true);
        }
        else {
            setDisplayMode(w, h, false);
        }
        this.setScreenSize(Display.getWidth(), Display.getHeight());
        try {
            this.saveOptions();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void changeCursor(final String img) {
    }
    
    public boolean loadOptions() throws IOException {
        final MultiStageBuilding msg = new MultiStageBuilding();
        final File inFile = new File(GameWindow.getCacheDir() + File.separator + "options.bin");
        final File newFile = new File(GameWindow.getCacheDir() + File.separator + "options.ini");
        if (!newFile.exists() && !inFile.exists()) {
            this.saveFolder = getMyDocumentFolder();
            final File newFile2 = new File(this.saveFolder);
            newFile2.mkdir();
            this.copyPasteFolders("mods");
            Translator.setLanguage(Language.FromString(System.getProperty("user.language").toUpperCase()));
            if (Translator.getAzertyMap().contains(Translator.getLanguage().name())) {
                this.setAzerty(true);
            }
            JoypadManager.instance.setControllerActive("XInputController0", true);
            JoypadManager.instance.setControllerActive("XInputController1", true);
            JoypadManager.instance.setControllerActive("XInputController2", true);
            JoypadManager.instance.setControllerActive("XInputController3", true);
            if (!GameServer.bServer) {
                try {
                    int w = 0;
                    int h = 0;
                    final DisplayMode[] modes = Display.getAvailableDisplayModes();
                    for (int n = 0; n < modes.length; ++n) {
                        if (modes[n].getWidth() > w && modes[n].getWidth() <= 1920) {
                            w = modes[n].getWidth();
                            h = modes[n].getHeight();
                        }
                    }
                    Core.width = w;
                    Core.height = h;
                }
                catch (LWJGLException e) {
                    e.printStackTrace();
                }
            }
            this.saveOptions();
            return false;
        }
        if (!newFile.exists() && inFile.exists()) {
            newFile.createNewFile();
            final FileWriter fw = new FileWriter(newFile);
            final FileInputStream inStream = new FileInputStream(inFile);
            final DataInputStream in = new DataInputStream(inStream);
            this.version = in.readInt();
            fw.write("version=" + this.version + "\r\n");
            Core.width = in.readInt();
            fw.write("width=" + Core.width + "\r\n");
            Core.height = in.readInt();
            fw.write("height=" + Core.height + "\r\n");
            Core.fullScreen = in.readBoolean();
            fw.write("fullScreen=" + Core.fullScreen + "\r\n");
            this.bUseShaders = in.readBoolean();
            fw.write("bUseShaders=" + Core.fullScreen + "\r\n");
            if (this.fileversion > 1) {
                this.vidMem = in.readInt();
            }
            if (this.fileversion > 2) {
                Core.bMultithreadedRendering = in.readBoolean();
            }
            try {
                if (this.version > 3) {
                    Translator.setLanguage(in.readInt());
                }
                this.saveFolder = getMyDocumentFolder();
            }
            catch (Exception ex2) {}
            fw.write("bMultithreadedRendering=" + Core.bMultithreadedRendering + "\r\n");
            if (this.version < 6) {
                this.copyPasteFolders("mods");
            }
            if (Translator.language == null) {
                Translator.setLanguage(Language.FromString(System.getProperty("user.language").toUpperCase()));
            }
            if (Translator.language == null) {
                Translator.setLanguage(Translator.getDefaultLanguage().index());
            }
            inStream.close();
            fw.write("language=" + Translator.language + "\r\n");
            fw.close();
        }
        if (inFile.exists()) {
            inFile.delete();
        }
        for (int n2 = 0; n2 < 4; ++n2) {
            this.setAutoZoom(n2, false);
        }
        final BufferedReader br = new BufferedReader(new FileReader(newFile));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("version=")) {
                    this.version = new Integer(line.replaceFirst("version=", ""));
                }
                else if (line.startsWith("width=")) {
                    Core.width = new Integer(line.replaceFirst("width=", ""));
                }
                else if (line.startsWith("height=")) {
                    Core.height = new Integer(line.replaceFirst("height=", ""));
                }
                else if (line.startsWith("fullScreen=")) {
                    Core.fullScreen = new Boolean(line.replaceFirst("fullScreen=", ""));
                }
                else if (line.startsWith("frameRate=")) {
                    PerformanceSettings.LockFPS = Integer.parseInt(line.replaceFirst("frameRate=", ""));
                }
                else if (line.startsWith("lighting=")) {
                    PerformanceSettings.LightingFrameSkip = Integer.parseInt(line.replaceFirst("lighting=", ""));
                }
                else if (line.startsWith("lightFPS=")) {
                    PerformanceSettings.instance.setLightingFPS(Integer.parseInt(line.replaceFirst("lightFPS=", "")));
                }
                else if (line.startsWith("bUseShaders=")) {
                    this.bUseShaders = new Boolean(line.replaceFirst("bUseShaders=", ""));
                }
                else if (line.startsWith("bMultithreadedRendering=")) {
                    Core.bMultithreadedRendering = new Boolean(line.replaceFirst("bMultithreadedRendering=", ""));
                }
                else if (line.startsWith("language=")) {
                    Translator.setLanguage(Language.FromString(line.replaceFirst("language=", "")));
                }
                else if (line.startsWith("zoom=")) {
                    Core.OptionZoom = Boolean.parseBoolean(line.replaceFirst("zoom=", ""));
                }
                else if (line.startsWith("autozoom=")) {
                    final String[] ss = line.replaceFirst("autozoom=", "").split(",");
                    for (int i = 0; i < ss.length; ++i) {
                        if (!ss[i].isEmpty()) {
                            final int playerIndex = Integer.parseInt(ss[i]);
                            if (playerIndex >= 1 && playerIndex <= 4) {
                                this.setAutoZoom(playerIndex - 1, true);
                            }
                        }
                    }
                }
                else if (line.startsWith("inventoryFont=")) {
                    Core.OptionInventoryFont = line.replaceFirst("inventoryFont=", "").trim();
                }
                else if (line.startsWith("measurementsFormat=")) {
                    Core.OptionMeasurementFormat = line.replaceFirst("measurementsFormat=", "").trim();
                }
                else if (line.startsWith("clockFormat=")) {
                    Core.OptionClockFormat = Integer.parseInt(line.replaceFirst("clockFormat=", ""));
                }
                else if (line.startsWith("clock24Hour=")) {
                    Core.OptionClock24Hour = Boolean.parseBoolean(line.replaceFirst("clock24Hour=", ""));
                }
                else if (line.startsWith("vsync=")) {
                    Core.OptionVSync = Boolean.parseBoolean(line.replaceFirst("vsync=", ""));
                }
                else if (line.startsWith("voiceEnable=")) {
                    Core.OptionVoiceEnable = Boolean.parseBoolean(line.replaceFirst("voiceEnable=", ""));
                }
                else if (line.startsWith("voiceMode=")) {
                    Core.OptionVoiceMode = Integer.parseInt(line.replaceFirst("voiceMode=", ""));
                }
                else if (line.startsWith("voiceVADMode=")) {
                    Core.OptionVoiceVADMode = Integer.parseInt(line.replaceFirst("voiceVADMode=", ""));
                }
                else if (line.startsWith("voiceVolumeMic=")) {
                    Core.OptionVoiceVolumeMic = Integer.parseInt(line.replaceFirst("voiceVolumeMic=", ""));
                }
                else if (line.startsWith("voiceVolumePlayers=")) {
                    Core.OptionVoiceVolumePlayers = Integer.parseInt(line.replaceFirst("voiceVolumePlayers=", ""));
                }
                else if (line.startsWith("voiceRecordDeviceName=")) {
                    Core.OptionVoiceRecordDeviceName = line.replaceFirst("voiceRecordDeviceName=", "");
                }
                else if (line.startsWith("soundVolume=")) {
                    Core.OptionSoundVolume = Integer.parseInt(line.replaceFirst("soundVolume=", ""));
                }
                else if (line.startsWith("musicVolume=")) {
                    Core.OptionMusicVolume = Integer.parseInt(line.replaceFirst("musicVolume=", ""));
                }
                else if (line.startsWith("ambientVolume=")) {
                    Core.OptionAmbientVolume = Integer.parseInt(line.replaceFirst("ambientVolume=", ""));
                }
                else if (line.startsWith("musicLibrary=")) {
                    Core.OptionMusicLibrary = Integer.parseInt(line.replaceFirst("musicLibrary=", ""));
                }
                else if (line.startsWith("fliesVolume=")) {
                    Core.OptionFliesVolume = Integer.parseInt(line.replaceFirst("fliesVolume=", ""));
                }
                else if (line.startsWith("heartVolume=")) {
                    Core.OptionHeartVolume = Integer.parseInt(line.replaceFirst("heartVolume=", ""));
                }
                else if (line.startsWith("reloadDifficulty=")) {
                    Core.OptionReloadDifficulty = Integer.parseInt(line.replaceFirst("reloadDifficulty=", ""));
                }
                else if (line.startsWith("rackProgress=")) {
                    Core.OptionRackProgress = Boolean.parseBoolean(line.replaceFirst("rackProgress=", ""));
                }
                else if (line.startsWith("controller=")) {
                    final String name = line.replaceFirst("controller=", "");
                    if (name.isEmpty()) {
                        continue;
                    }
                    JoypadManager.instance.setControllerActive(name, true);
                }
                else if (line.startsWith("numberOf3D=")) {
                    PerformanceSettings.numberOf3D = Integer.parseInt(line.replaceFirst("numberOf3D=", ""));
                    if (PerformanceSettings.numberOf3D < 0) {
                        PerformanceSettings.numberOf3D = 0;
                    }
                    if (PerformanceSettings.numberOf3D <= 9) {
                        continue;
                    }
                    PerformanceSettings.numberOf3D = 9;
                }
                else if (line.startsWith("modelsEnabled")) {
                    PerformanceSettings.modelsEnabled = Boolean.parseBoolean(line.replaceFirst("modelsEnabled=", ""));
                }
                else if (line.startsWith("corpses3D")) {
                    PerformanceSettings.corpses3D = Boolean.parseBoolean(line.replaceFirst("corpses3D=", ""));
                }
                else if (line.startsWith("tutorialDone=")) {
                    this.tutorialDone = Boolean.parseBoolean(line.replaceFirst("tutorialDone=", ""));
                }
                else if (line.startsWith("bloodDecals=")) {
                    this.setOptionBloodDecals(Integer.parseInt(line.replaceFirst("bloodDecals=", "")));
                }
                else if (line.startsWith("borderless=")) {
                    Core.OptionBorderlessWindow = Boolean.parseBoolean(line.replaceFirst("borderless=", ""));
                }
                else if (line.startsWith("textureCompression=")) {
                    Core.OptionTextureCompression = Boolean.parseBoolean(line.replaceFirst("textureCompression=", ""));
                }
                else if (line.startsWith("texture2x=")) {
                    Core.OptionTexture2x = Boolean.parseBoolean(line.replaceFirst("texture2x=", ""));
                }
                else if (line.startsWith("zoomLevels1x=")) {
                    Core.OptionZoomLevels1x = line.replaceFirst("zoomLevels1x=", "");
                }
                else if (line.startsWith("zoomLevels2x=")) {
                    Core.OptionZoomLevels2x = line.replaceFirst("zoomLevels2x=", "");
                }
                else if (line.startsWith("doneNewSaveFolder=")) {
                    this.doneNewSaveFolder = Boolean.parseBoolean(line.replaceFirst("doneNewSaveFolder=", ""));
                }
                else if (line.startsWith("contentTranslationsEnabled=")) {
                    Core.OptionEnableContentTranslations = Boolean.parseBoolean(line.replaceFirst("contentTranslationsEnabled=", ""));
                }
                else if (line.startsWith("showYourUsername=")) {
                    this.showYourUsername = Boolean.parseBoolean(line.replaceFirst("showYourUsername=", ""));
                }
                else if (line.startsWith("riversideDone=")) {
                    this.riversideDone = Boolean.parseBoolean(line.replaceFirst("riversideDone=", ""));
                }
                else if (line.startsWith("rosewoodSpawnDone=")) {
                    this.rosewoodSpawnDone = Boolean.parseBoolean(line.replaceFirst("rosewoodSpawnDone=", ""));
                }
                else if (line.startsWith("mpTextColor=")) {
                    final String[] colors = line.replaceFirst("mpTextColor=", "").split(",");
                    float r = Float.parseFloat(colors[0]);
                    float g = Float.parseFloat(colors[1]);
                    float b = Float.parseFloat(colors[2]);
                    if (r < 0.19f) {
                        r = 0.19f;
                    }
                    if (g < 0.19f) {
                        g = 0.19f;
                    }
                    if (b < 0.19f) {
                        b = 0.19f;
                    }
                    this.mpTextColor = new ColorInfo(r, g, b, 1.0f);
                }
                else if (line.startsWith("objHighlightColor=")) {
                    final String[] colors = line.replaceFirst("objHighlightColor=", "").split(",");
                    float r = Float.parseFloat(colors[0]);
                    float g = Float.parseFloat(colors[1]);
                    float b = Float.parseFloat(colors[2]);
                    if (r < 0.19f) {
                        r = 0.19f;
                    }
                    if (g < 0.19f) {
                        g = 0.19f;
                    }
                    if (b < 0.19f) {
                        b = 0.19f;
                    }
                    this.objectHighlitedColor = new ColorInfo(r, g, b, 1.0f);
                }
                else if (line.startsWith("seenNews=")) {
                    this.setSeenUpdateText(line.replaceFirst("seenNews=", ""));
                }
                else if (line.startsWith("toggleToRun=")) {
                    this.setToggleToRun(Boolean.parseBoolean(line.replaceFirst("toggleToRun=", "")));
                }
                else if (line.startsWith("celsius=")) {
                    this.setCelsius(Boolean.parseBoolean(line.replaceFirst("celsius=", "")));
                }
                else {
                    if (!line.startsWith("mapOrder=")) {
                        continue;
                    }
                    this.mapOrder = new LinkedList<String>();
                    if (this.version < 7) {
                        line = "mapOrder=";
                    }
                    final String[] split;
                    final String[] order = split = line.replaceFirst("mapOrder=", "").split(";");
                    for (final String map : split) {
                        if (!map.isEmpty()) {
                            this.mapOrder.add(map);
                        }
                    }
                }
            }
            OnceEvery.FPS = PerformanceSettings.LockFPS;
            if (Translator.language == null) {
                Translator.setLanguage(Language.FromString(System.getProperty("user.language").toUpperCase()));
            }
            if (Translator.language == null) {
                Translator.setLanguage(Translator.getDefaultLanguage().index());
            }
            if (!this.doneNewSaveFolder) {
                final File newSaveFolder = new File(GameWindow.getSaveDir());
                newSaveFolder.mkdir();
                final ArrayList<String> gameModes = new ArrayList<String>();
                gameModes.add("Beginner");
                gameModes.add("Survival");
                gameModes.add("A Really CD DA");
                gameModes.add("LastStand");
                gameModes.add("Opening Hours");
                gameModes.add("Sandbox");
                gameModes.add("Tutorial");
                gameModes.add("Winter is Coming");
                gameModes.add("You Have One Day");
                File previousSave = null;
                File newSave = null;
                try {
                    for (final String path : gameModes) {
                        previousSave = new File(GameWindow.getCacheDir() + File.separator + path);
                        newSave = new File(GameWindow.getSaveDir() + File.separator + path);
                        if (previousSave.exists()) {
                            newSave.mkdir();
                            Files.move(previousSave.toPath(), newSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                }
                catch (Exception ex3) {}
                this.doneNewSaveFolder = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            br.close();
        }
        this.saveOptions();
        return true;
    }
    
    private void copyPasteFolders(final String dir) {
        final File srcFolder = new File(dir).getAbsoluteFile();
        if (srcFolder.exists()) {
            this.searchFolders(srcFolder, dir);
        }
    }
    
    private void searchFolders(final File file, final String relative) {
        if (file.isDirectory()) {
            final File newFile = new File(this.saveFolder + File.separator + relative);
            newFile.mkdir();
            final String[] internalNames = file.list();
            for (int i = 0; i < internalNames.length; ++i) {
                this.searchFolders(new File(file.getAbsolutePath() + File.separator + internalNames[i]), relative + File.separator + internalNames[i]);
            }
        }
        else {
            this.copyPasteFile(file, relative);
        }
    }
    
    private void copyPasteFile(final File file, final String relative) {
        FileOutputStream outStream = null;
        FileInputStream inStream = null;
        try {
            final File newFile = new File(this.saveFolder + File.separator + relative);
            newFile.createNewFile();
            outStream = new FileOutputStream(newFile);
            inStream = new FileInputStream(file);
            outStream.getChannel().transferFrom(inStream.getChannel(), 0L, file.length());
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }
    
    public static String getMyDocumentFolder() {
        return GameWindow.getCacheDir();
    }
    
    public void saveOptions() throws IOException {
        final File newFile = new File(GameWindow.getCacheDir() + File.separator + "options.ini");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        final FileWriter fw = new FileWriter(newFile);
        try {
            fw.write("version=" + this.fileversion + "\r\n");
            fw.write("width=" + this.getScreenWidth() + "\r\n");
            fw.write("height=" + this.getScreenHeight() + "\r\n");
            fw.write("fullScreen=" + Core.fullScreen + "\r\n");
            fw.write("frameRate=" + PerformanceSettings.LockFPS + "\r\n");
            fw.write("lighting=" + PerformanceSettings.LightingFrameSkip + "\r\n");
            fw.write("lightFPS=" + PerformanceSettings.LightingFPS + "\r\n");
            fw.write("bUseShaders=" + this.bUseShaders + "\r\n");
            fw.write("vidMem=" + this.vidMem + "\r\n");
            fw.write("bMultithreadedRendering=" + Core.bMultithreadedRendering + "\r\n");
            fw.write("language=" + Translator.getLanguage() + "\r\n");
            fw.write("zoom=" + Core.OptionZoom + "\r\n");
            fw.write("inventoryFont=" + Core.OptionInventoryFont + "\r\n");
            fw.write("clockFormat=" + Core.OptionClockFormat + "\r\n");
            fw.write("clock24Hour=" + Core.OptionClock24Hour + "\r\n");
            fw.write("measurementsFormat=" + Core.OptionMeasurementFormat + "\r\n");
            String autoZoom = "";
            for (int playerIndex = 0; playerIndex < 4; ++playerIndex) {
                if (Core.bAutoZoom[playerIndex]) {
                    if (!autoZoom.isEmpty()) {
                        autoZoom += ",";
                    }
                    autoZoom += playerIndex + 1;
                }
            }
            fw.write("autozoom=" + autoZoom + "\r\n");
            fw.write("vsync=" + Core.OptionVSync + "\r\n");
            fw.write("soundVolume=" + Core.OptionSoundVolume + "\r\n");
            fw.write("ambientVolume=" + Core.OptionAmbientVolume + "\r\n");
            fw.write("musicVolume=" + Core.OptionMusicVolume + "\r\n");
            fw.write("musicLibrary=" + Core.OptionMusicLibrary + "\r\n");
            fw.write("fliesVolume=" + Core.OptionFliesVolume + "\r\n");
            fw.write("heartVolume=" + Core.OptionHeartVolume + "\r\n");
            fw.write("voiceEnable=" + Core.OptionVoiceEnable + "\r\n");
            fw.write("voiceMode=" + Core.OptionVoiceMode + "\r\n");
            fw.write("voiceVADMode=" + Core.OptionVoiceVADMode + "\r\n");
            fw.write("voiceVolumeMic=" + Core.OptionVoiceVolumeMic + "\r\n");
            fw.write("voiceVolumePlayerse=" + Core.OptionVoiceVolumePlayers + "\r\n");
            fw.write("voiceRecordDeviceName=" + Core.OptionVoiceRecordDeviceName + "\r\n");
            fw.write("reloadDifficulty=" + Core.OptionReloadDifficulty + "\r\n");
            fw.write("rackProgress=" + Core.OptionRackProgress + "\r\n");
            for (final String name : JoypadManager.instance.ActiveControllerNames) {
                fw.write("controller=" + name + "\r\n");
            }
            fw.write("numberOf3D=" + PerformanceSettings.numberOf3D + "\r\n");
            fw.write("modelsEnabled=" + PerformanceSettings.modelsEnabled + "\r\n");
            fw.write("corpses3D=" + PerformanceSettings.corpses3D + "\r\n");
            fw.write("tutorialDone=" + this.isTutorialDone() + "\r\n");
            fw.write("bloodDecals=" + Core.OptionBloodDecals + "\r\n");
            fw.write("borderless=" + Core.OptionBorderlessWindow + "\r\n");
            fw.write("textureCompression=" + Core.OptionTextureCompression + "\r\n");
            fw.write("texture2x=" + Core.OptionTexture2x + "\r\n");
            fw.write("zoomLevels1x=" + Core.OptionZoomLevels1x + "\r\n");
            fw.write("zoomLevels2x=" + Core.OptionZoomLevels2x + "\r\n");
            fw.write("doneNewSaveFolder=" + this.doneNewSaveFolder + "\r\n");
            fw.write("contentTranslationsEnabled=" + Core.OptionEnableContentTranslations + "\r\n");
            fw.write("showYourUsername=" + this.showYourUsername + "\r\n");
            fw.write("rosewoodSpawnDone=" + this.rosewoodSpawnDone + "\r\n");
            if (this.mpTextColor != null) {
                fw.write("mpTextColor=" + this.mpTextColor.r + "," + this.mpTextColor.g + "," + this.mpTextColor.b + "\r\n");
            }
            fw.write("objHighlightColor=" + this.objectHighlitedColor.r + "," + this.objectHighlitedColor.g + "," + this.objectHighlitedColor.b + "\r\n");
            fw.write("seenNews=" + this.getSeenUpdateText() + "\r\n");
            fw.write("toggleToRun=" + this.isToggleToRun() + "\r\n");
            fw.write("celsius=" + this.isCelsius() + "\r\n");
            fw.write("riversideDone=" + this.isRiversideDone() + "\r\n");
            fw.write("mapOrder=");
            String mapOrderStr = "";
            if (this.mapOrder != null) {
                for (int i = 0; i < this.mapOrder.size(); ++i) {
                    mapOrderStr = mapOrderStr + this.mapOrder.get(i) + ((i < this.mapOrder.size() - 1) ? ";" : "");
                }
                fw.write(mapOrderStr + "\r\n");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            fw.close();
        }
        OnceEvery.FPS = PerformanceSettings.LockFPS;
    }
    
    public void setWindowed(final boolean b) {
        RenderThread.borrowContext();
        if (b != Core.fullScreen) {
            setDisplayMode(this.getScreenWidth(), this.getScreenHeight(), b);
        }
        Core.fullScreen = b;
        if (Core.fakefullscreen) {
            Display.setResizable(false);
        }
        else {
            Display.setResizable(!b);
        }
        try {
            this.saveOptions();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        RenderThread.returnContext();
    }
    
    public boolean isFullScreen() {
        return Core.fullScreen;
    }
    
    public static void restartApplication(final Runnable runBeforeRestart) throws IOException {
        try {
            final String java = System.getProperty("java.home") + "/bin/java";
            final List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
            final StringBuffer vmArgsOneLine = new StringBuffer();
            for (final String arg : vmArguments) {
                if (!arg.contains("-agentlib")) {
                    vmArgsOneLine.append(arg);
                    vmArgsOneLine.append(" ");
                }
            }
            final StringBuffer cmd = new StringBuffer(java + " " + (Object)vmArgsOneLine);
            final String[] mainCommand = System.getProperty("sun.java.command").split(" ");
            if (mainCommand[0].endsWith(".jar")) {
                cmd.append("-jar " + new File(mainCommand[0]).getPath());
            }
            else {
                cmd.append("-cp " + System.getProperty("java.class.path") + " " + mainCommand[0]);
            }
            for (int i = 1; i < mainCommand.length; ++i) {
                cmd.append(" ");
                cmd.append(mainCommand[i]);
            }
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        DebugLog.log("Relaunching: " + cmd.toString());
                        Runtime.getRuntime().exec(cmd.toString());
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (runBeforeRestart != null) {
                runBeforeRestart.run();
            }
            System.exit(0);
        }
        catch (Exception e) {
            throw new IOException("Error while trying to restart the application", e);
        }
    }
    
    public KahluaTable getScreenModes() {
        final ArrayList<String> test = new ArrayList<String>();
        final KahluaTable t = LuaManager.platform.newTable();
        final File newFile = new File(LuaManager.getLuaCacheDir() + File.separator + "screenresolution.ini");
        int c = 1;
        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
                final FileWriter fw = new FileWriter(newFile);
                Integer w = 0;
                Integer h = 0;
                final DisplayMode[] modes = Display.getAvailableDisplayModes();
                for (int n = 0; n < modes.length; ++n) {
                    w = modes[n].getWidth();
                    h = modes[n].getHeight();
                    if (!test.contains(w + " x " + h)) {
                        t.rawset(c, (Object)(w + " x " + h));
                        fw.write(w + " x " + h + " \r\n");
                        test.add(w + " x " + h);
                        ++c;
                    }
                }
                fw.close();
            }
            else {
                final BufferedReader br = new BufferedReader(new FileReader(newFile));
                String line = null;
                while ((line = br.readLine()) != null) {
                    t.rawset(c, (Object)line.trim());
                    ++c;
                }
                br.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    
    public static void setDisplayMode(final int width, final int height, final boolean fullscreen) {
        if (Display.getWidth() == width && Display.getHeight() == height && Display.isFullscreen() == fullscreen) {
            return;
        }
        RenderThread.borrowContext();
        Core.fullScreen = fullscreen;
        try {
            DisplayMode targetDisplayMode = null;
            if (fullscreen) {
                final DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;
                DisplayMode closest = null;
                for (int i = 0; i < modes.length; ++i) {
                    final DisplayMode current = modes[i];
                    if (current.getWidth() == width && current.getHeight() == height && current.isFullscreenCapable()) {
                        if ((targetDisplayMode == null || current.getFrequency() >= freq) && (targetDisplayMode == null || current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                            targetDisplayMode = current;
                            freq = targetDisplayMode.getFrequency();
                        }
                        if (current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel() && current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                    if (current.isFullscreenCapable() && (closest == null || Math.abs(current.getWidth() - width) < Math.abs(closest.getWidth() - width) || (current.getWidth() == closest.getWidth() && current.getFrequency() > freq))) {
                        closest = current;
                        freq = current.getFrequency();
                        System.out.println("closest width=" + closest.getWidth() + " freq=" + closest.getFrequency());
                    }
                }
                if (targetDisplayMode == null && closest != null) {
                    targetDisplayMode = closest;
                }
            }
            else {
                targetDisplayMode = new DisplayMode(width, height);
            }
            if (targetDisplayMode == null) {
                RenderThread.returnContext();
                DebugLog.log("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
                return;
            }
            if (fullscreen) {
                Display.setDisplayModeAndFullscreen(targetDisplayMode);
            }
            else {
                Display.setDisplayMode(targetDisplayMode);
                Display.setFullscreen(fullscreen);
            }
            if (!fullscreen && Core.OptionBorderlessWindow) {
                Display.setResizable(false);
            }
            else if (!fullscreen && !Core.fakefullscreen) {
                Display.setResizable(false);
                Display.setResizable(true);
            }
            if (Display.isCreated()) {
                DebugLog.log("Display mode changed to " + Display.getWidth() + "x" + Display.getHeight() + " freq=" + Display.getDisplayMode().getFrequency() + " fullScreen=" + Display.isFullscreen());
            }
        }
        catch (LWJGLException e) {
            DebugLog.log("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
        }
        RenderThread.returnContext();
    }
    
    public void updateKeyboard() {
        if (Core.CurrentTextEntryBox == null) {
            while (Keyboard.next()) {}
        }
        else if (Core.CurrentTextEntryBox.IsEditable && Core.CurrentTextEntryBox.DoingTextEntry) {
            while (Keyboard.next()) {
				if (!Keyboard.getEventKeyState()) {// XXX AddedCodesInHere: Ctrl Key when getEventKeyState() == false(key realease)
					if (Keyboard.getEventKey() == 29) {
						KoreanCore.switchInputMode();
					}
				}
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == 28) {
                        boolean CommandEntered = false;
                        if (UIManager.getDebugConsole() != null && Core.CurrentTextEntryBox == UIManager.getDebugConsole().CommandLine) {
                            CommandEntered = true;
                        }
                        if (Core.CurrentTextEntryBox != null) {
                            final UITextBox2 b = Core.CurrentTextEntryBox;
                            if (b.multipleLine) {
                                if (b.Lines.size() < b.getMaxLines()) {
                                    if (b.TextEntryCursorPos != b.ToSelectionIndex) {
                                        final int l = Math.min(b.TextEntryCursorPos, b.ToSelectionIndex);
                                        final int h = Math.max(b.TextEntryCursorPos, b.ToSelectionIndex);
                                        if (b.internalText.length() > 0) {
                                            b.internalText = b.internalText.substring(0, l) + "\n" + b.internalText.substring(h);
                                        }
                                        else {
                                            b.internalText = "\n";
                                        }
                                        b.TextEntryCursorPos = l + 1;
                                    }
                                    else {
                                        final int textOffset = b.TextEntryCursorPos;
                                        final String text = b.internalText.substring(0, textOffset) + "\n" + b.internalText.substring(textOffset);
                                        b.SetText(text);
                                        b.TextEntryCursorPos = textOffset + 1;
                                    }
                                    b.ToSelectionIndex = b.TextEntryCursorPos;
                                    b.CursorLine = b.toDisplayLine(b.TextEntryCursorPos);
                                }
                            }
                            else {
                                Core.CurrentTextEntryBox.onCommandEntered();
                            }
                        }
                        if (!CommandEntered || (GameClient.bClient && GameClient.accessLevel.equals(""))) {
                            continue;
                        }
                        UIManager.getDebugConsole().ProcessCommand();
                    }
                    else if (Keyboard.getEventKey() == 1) {
                        if (Core.CurrentTextEntryBox == null) {
                            continue;
                        }
                        Core.CurrentTextEntryBox.onOtherKey(1);
                        GameKeyboard.eatKeyPress(1);
                    }
                    else {
                        if (Keyboard.getEventKey() == 15) {
                            continue;
                        }
                        if (Keyboard.getEventKey() == 58) {
                            continue;
                        }
                        if (Keyboard.getEventKey() == 199) {
                            Core.CurrentTextEntryBox.TextEntryCursorPos = 0;
                            if (!Core.CurrentTextEntryBox.Lines.isEmpty()) {
                                Core.CurrentTextEntryBox.TextEntryCursorPos = Core.CurrentTextEntryBox.TextOffsetOfLineStart.get(Core.CurrentTextEntryBox.CursorLine);
                            }
                            if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                                Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                            }
                            Core.CurrentTextEntryBox.resetBlink();
                        }
                        else if (Keyboard.getEventKey() == 207) {
                            Core.CurrentTextEntryBox.TextEntryCursorPos = Core.CurrentTextEntryBox.internalText.length();
                            if (!Core.CurrentTextEntryBox.Lines.isEmpty()) {
                                Core.CurrentTextEntryBox.TextEntryCursorPos = Core.CurrentTextEntryBox.TextOffsetOfLineStart.get(Core.CurrentTextEntryBox.CursorLine) + Core.CurrentTextEntryBox.Lines.get(Core.CurrentTextEntryBox.CursorLine).length();
                            }
                            if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                                Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                            }
                            Core.CurrentTextEntryBox.resetBlink();
                        }
                        else if (Keyboard.getEventKey() == 200) {
                            if (Core.CurrentTextEntryBox.CursorLine > 0) {
                                int column = Core.CurrentTextEntryBox.TextEntryCursorPos - Core.CurrentTextEntryBox.TextOffsetOfLineStart.get(Core.CurrentTextEntryBox.CursorLine);
                                final UITextBox2 currentTextEntryBox = Core.CurrentTextEntryBox;
                                --currentTextEntryBox.CursorLine;
                                if (column > Core.CurrentTextEntryBox.Lines.get(Core.CurrentTextEntryBox.CursorLine).length()) {
                                    column = Core.CurrentTextEntryBox.Lines.get(Core.CurrentTextEntryBox.CursorLine).length();
                                }
                                Core.CurrentTextEntryBox.TextEntryCursorPos = Core.CurrentTextEntryBox.TextOffsetOfLineStart.get(Core.CurrentTextEntryBox.CursorLine) + column;
                                if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                                    Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                }
                            }
                            Core.CurrentTextEntryBox.onPressUp();
                        }
                        else if (Keyboard.getEventKey() == 208) {
                            if (Core.CurrentTextEntryBox.Lines.size() - 1 > Core.CurrentTextEntryBox.CursorLine && Core.CurrentTextEntryBox.CursorLine + 1 < Core.CurrentTextEntryBox.getMaxLines()) {
                                int column = Core.CurrentTextEntryBox.TextEntryCursorPos - Core.CurrentTextEntryBox.TextOffsetOfLineStart.get(Core.CurrentTextEntryBox.CursorLine);
                                final UITextBox2 currentTextEntryBox2 = Core.CurrentTextEntryBox;
                                ++currentTextEntryBox2.CursorLine;
                                if (column > Core.CurrentTextEntryBox.Lines.get(Core.CurrentTextEntryBox.CursorLine).length()) {
                                    column = Core.CurrentTextEntryBox.Lines.get(Core.CurrentTextEntryBox.CursorLine).length();
                                }
                                Core.CurrentTextEntryBox.TextEntryCursorPos = Core.CurrentTextEntryBox.TextOffsetOfLineStart.get(Core.CurrentTextEntryBox.CursorLine) + column;
                                if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                                    Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                }
                            }
                            Core.CurrentTextEntryBox.onPressDown();
                        }
                        else {
                            if (Keyboard.getEventKey() == 29) {
                                continue;
                            }
                            if (Keyboard.getEventKey() == 157) {
                                continue;
                            }
                            if (Keyboard.getEventKey() == 56) {
                                continue;
                            }
                            if (Keyboard.getEventKey() == 184) {
                                continue;
                            }
                            if (Keyboard.getEventKey() == 203) {
                                final UITextBox2 currentTextEntryBox3 = Core.CurrentTextEntryBox;
                                --currentTextEntryBox3.TextEntryCursorPos;
                                if (Core.CurrentTextEntryBox.TextEntryCursorPos < 0) {
                                    Core.CurrentTextEntryBox.TextEntryCursorPos = 0;
                                }
                                if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                                    Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                }
                                Core.CurrentTextEntryBox.resetBlink();
                            }
                            else if (Keyboard.getEventKey() == 205) {
                                final UITextBox2 currentTextEntryBox4 = Core.CurrentTextEntryBox;
                                ++currentTextEntryBox4.TextEntryCursorPos;
                                if (Core.CurrentTextEntryBox.TextEntryCursorPos > Core.CurrentTextEntryBox.internalText.length()) {
                                    Core.CurrentTextEntryBox.TextEntryCursorPos = Core.CurrentTextEntryBox.internalText.length();
                                }
                                if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
                                    Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                }
                                Core.CurrentTextEntryBox.resetBlink();
                            }
                            else {
                                if (Core.CurrentTextEntryBox == null || !Core.CurrentTextEntryBox.DoingTextEntry) {
                                    continue;
                                }
                                if ((Keyboard.getEventKey() == 211 || Keyboard.getEventKey() == 14) && Core.CurrentTextEntryBox.TextEntryCursorPos != Core.CurrentTextEntryBox.ToSelectionIndex) {
                                    final int i = Math.min(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                    final int h2 = Math.max(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                    Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, i) + Core.CurrentTextEntryBox.internalText.substring(h2);
                                    Core.CurrentTextEntryBox.CursorLine = Core.CurrentTextEntryBox.toDisplayLine(i);
                                    Core.CurrentTextEntryBox.ToSelectionIndex = i;
                                    Core.CurrentTextEntryBox.TextEntryCursorPos = i;
                                    Core.CurrentTextEntryBox.onTextChange();
                                }
                                else if (Keyboard.getEventKey() == 211) {
                                    if (Core.CurrentTextEntryBox.internalText.length() <= 0 || Core.CurrentTextEntryBox.TextEntryCursorPos >= Core.CurrentTextEntryBox.internalText.length()) {
                                        continue;
                                    }
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos > 0) {
                                        Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, Core.CurrentTextEntryBox.TextEntryCursorPos) + Core.CurrentTextEntryBox.internalText.substring(Core.CurrentTextEntryBox.TextEntryCursorPos + 1);
                                    }
                                    else {
                                        Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(1);
                                    }
                                    Core.CurrentTextEntryBox.onTextChange();
                                }
                                else if (Keyboard.getEventKey() == 14) {// XXX ModifiedCodesInHere: Backspace
                                    if (Core.CurrentTextEntryBox.internalText.length() <= 0 || Core.CurrentTextEntryBox.TextEntryCursorPos <= 0) {
                                        continue;
                                    }
									/*
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos > Core.CurrentTextEntryBox.internalText.length()) {
                                        Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, Core.CurrentTextEntryBox.internalText.length() - 1);
                                    }
                                    else {
                                        final int textOffset2 = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                        Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, textOffset2 - 1) + Core.CurrentTextEntryBox.internalText.substring(textOffset2);
                                    }
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos > 0) {
                                        final UITextBox2 currentTextEntryBox5 = Core.CurrentTextEntryBox;
                                        --currentTextEntryBox5.TextEntryCursorPos;
                                        Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                    }
									*/
									Result result = KoreanCore.deleteKorean(CurrentTextEntryBox.internalText, CurrentTextEntryBox.TextEntryCursorPos, CurrentTextEntryBox.ToSelectionIndex);
									CurrentTextEntryBox.internalText = result.STR;
                                    CurrentTextEntryBox.TextEntryCursorPos = result.CURSOR;
                                    CurrentTextEntryBox.ToSelectionIndex = result.SELECTION;

                                    Core.CurrentTextEntryBox.onTextChange();
                                }
                                else if ((Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)) && Keyboard.getEventKey() == 47) {
                                    final String clip = Clipboard.getClipboard();
                                    if (clip == null) {
                                        continue;
                                    }
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos != Core.CurrentTextEntryBox.ToSelectionIndex) {
                                        final int j = Math.min(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                        final int h3 = Math.max(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                        Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, j) + clip + Core.CurrentTextEntryBox.internalText.substring(h3);
                                        Core.CurrentTextEntryBox.ToSelectionIndex = j + clip.length();
                                        Core.CurrentTextEntryBox.TextEntryCursorPos = j + clip.length();
                                    }
                                    else {
                                        if (Core.CurrentTextEntryBox.TextEntryCursorPos < Core.CurrentTextEntryBox.internalText.length()) {
                                            Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, Core.CurrentTextEntryBox.TextEntryCursorPos) + clip + Core.CurrentTextEntryBox.internalText.substring(Core.CurrentTextEntryBox.TextEntryCursorPos);
                                        }
                                        else {
                                            Core.CurrentTextEntryBox.internalText += clip;
                                        }
                                        final UITextBox2 currentTextEntryBox6 = Core.CurrentTextEntryBox;
                                        currentTextEntryBox6.TextEntryCursorPos += clip.length();
                                        final UITextBox2 currentTextEntryBox7 = Core.CurrentTextEntryBox;
                                        currentTextEntryBox7.ToSelectionIndex += clip.length();
                                    }
                                    Core.CurrentTextEntryBox.onTextChange();
                                }
                                else if ((Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)) && Keyboard.getEventKey() == 46) {
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos == Core.CurrentTextEntryBox.ToSelectionIndex) {
                                        continue;
                                    }
                                    final int i = Math.min(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                    final int h2 = Math.max(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                    Core.CurrentTextEntryBox.updateText();
                                    final String newClip = Core.CurrentTextEntryBox.Text.substring(i, h2);
                                    if (newClip == null || newClip.length() <= 0) {
                                        continue;
                                    }
                                    Clipboard.setClipboard(newClip);
                                }
                                else if ((Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)) && Keyboard.getEventKey() == 45) {
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos == Core.CurrentTextEntryBox.ToSelectionIndex) {
                                        continue;
                                    }
                                    final int i = Math.min(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                    final int h2 = Math.max(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                    Core.CurrentTextEntryBox.updateText();
                                    final String newClip = Core.CurrentTextEntryBox.Text.substring(i, h2);
                                    if (newClip != null && newClip.length() > 0) {
                                        Clipboard.setClipboard(newClip);
                                    }
                                    Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, i) + Core.CurrentTextEntryBox.internalText.substring(h2);
                                    Core.CurrentTextEntryBox.ToSelectionIndex = i;
                                    Core.CurrentTextEntryBox.TextEntryCursorPos = i;
                                }
                                else if ((Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)) && Keyboard.getEventKey() == 30) {
                                    Core.CurrentTextEntryBox.TextEntryCursorPos = 0;
                                    Core.CurrentTextEntryBox.ToSelectionIndex = Core.CurrentTextEntryBox.internalText.length();
                                }
                                else {
                                    if (Core.CurrentTextEntryBox.ignoreFirst || Keyboard.getEventKey() == 42 || Keyboard.getEventKey() == 54 || Core.CurrentTextEntryBox.internalText.length() > Core.CurrentTextEntryBox.TextEntryMaxLength) {
                                        continue;
                                    }
                                    if (Core.CurrentTextEntryBox.isOnlyNumbers() && Keyboard.getEventCharacter() != '.') {
                                        try {
                                            Double.parseDouble(String.valueOf(Keyboard.getEventCharacter()));
                                        }
                                        catch (Exception e) {
                                            return;
                                        }
                                    }
                                    if (Core.CurrentTextEntryBox.TextEntryCursorPos != Core.CurrentTextEntryBox.ToSelectionIndex) {
                                        final int i = Math.min(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                        final int h2 = Math.max(Core.CurrentTextEntryBox.TextEntryCursorPos, Core.CurrentTextEntryBox.ToSelectionIndex);
                                        if (Core.CurrentTextEntryBox.internalText.length() > 0) {
                                            Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, i) + Keyboard.getEventCharacter() + Core.CurrentTextEntryBox.internalText.substring(h2);
                                        }
                                        else {
                                            Core.CurrentTextEntryBox.internalText = "" + Keyboard.getEventCharacter();
                                        }
                                        Core.CurrentTextEntryBox.ToSelectionIndex = i + 1;
                                        Core.CurrentTextEntryBox.TextEntryCursorPos = i + 1;
                                    }
                                    else {// XXX ModifiedCodesInHere: Common Input
                                        final int textOffset2 = Core.CurrentTextEntryBox.TextEntryCursorPos;
                                        if (textOffset2 < Core.CurrentTextEntryBox.internalText.length()) {
                                            Core.CurrentTextEntryBox.internalText = Core.CurrentTextEntryBox.internalText.substring(0, textOffset2) + Keyboard.getEventCharacter() + Core.CurrentTextEntryBox.internalText.substring(textOffset2);
                                        }
                                        else {
                                            //final StringBuilder sb = new StringBuilder();
                                            final UITextBox2 currentTextEntryBox8 = Core.CurrentTextEntryBox;
                                            //currentTextEntryBox8.internalText = sb.append(currentTextEntryBox8.internalText).append(Keyboard.getEventCharacter()).toString();
                                            Result result = KoreanCore.writeKorean(currentTextEntryBox8.internalText, currentTextEntryBox8.TextEntryCursorPos, currentTextEntryBox8.ToSelectionIndex, Keyboard.getEventCharacter());
                                            currentTextEntryBox8.internalText = result.STR;
                                            currentTextEntryBox8.TextEntryCursorPos = result.CURSOR;
                                            currentTextEntryBox8.ToSelectionIndex = result.SELECTION;
                                        }
                                        final UITextBox2 currentTextEntryBox9 = Core.CurrentTextEntryBox;
                                        ++currentTextEntryBox9.TextEntryCursorPos;
                                        final UITextBox2 currentTextEntryBox10 = Core.CurrentTextEntryBox;
                                        ++currentTextEntryBox10.ToSelectionIndex;
                                        Core.CurrentTextEntryBox.onTextChange();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (Core.CurrentTextEntryBox != null && Core.CurrentTextEntryBox.ignoreFirst) {
                Core.CurrentTextEntryBox.ignoreFirst = false;
            }
        }
    }
    
    public void quit() {
        if (IsoPlayer.instance != null) {
            Core.bExiting = true;
        }
        else {
            GameClient.instance.Shutdown();
            SteamUtils.shutdown();
            System.exit(0);
        }
    }
    
    public void exitToMenu() {
        Core.bExiting = true;
    }
    
    public void quitToDesktop() {
        GameWindow.closeRequested = true;
    }
    
    public boolean supportRes(final int width, final int height) throws LWJGLException {
        final DisplayMode[] modes = Display.getAvailableDisplayModes();
        final boolean bFound = false;
        for (int n = 0; n < modes.length; ++n) {
            if (modes[n].getWidth() == width && modes[n].getHeight() == height && modes[n].isFullscreenCapable()) {
                return true;
            }
        }
        return false;
    }
    
    public void init(final int width, final int height) throws LWJGLException {
        System.setProperty("org.lwjgl.opengl.Window.undecorated", Core.OptionBorderlessWindow ? "true" : "false");
        Display.setVSyncEnabled(Core.OptionVSync);
        if (!System.getProperty("os.name").contains("OS X") && !System.getProperty("os.name").startsWith("Win")) {
            DebugLog.log("Creating display. If this fails, you may need to install xrandr.");
        }
        setDisplayMode(width, height, Core.fullScreen);
        try {
            Display.create(new PixelFormat(32, 0, 24, 8, 0));
        }
        catch (LWJGLException ex) {
            Display.destroy();
            Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
            Display.create(new PixelFormat(32, 0, 24, 8, 0));
        }
        Core.fullScreen = Display.isFullscreen();
        DebugLog.log("OpenGL version: " + GL11.glGetString(7938));
        DebugLog.log("Desktop resolution " + Display.getDesktopDisplayMode().getWidth() + "x" + Display.getDesktopDisplayMode().getHeight());
        DebugLog.log("Initial resolution " + Core.width + "x" + Core.height + " fullScreen=" + Core.fullScreen);
        GLVertexBufferObject.init();
        if (!Core.bIsSteam || GameWindow.OSValidator.isWindows()) {}
        Display.setVSyncEnabled(Core.OptionVSync);
        GL11.glEnable(3553);
        IndieGL.glBlendFunc(770, 771);
        GL11.glTexEnvf(8960, 8704, 8448.0f);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    private boolean setupMultiFBO() {
        try {
            if (!this.OffscreenBuffer.test()) {
                return false;
            }
            this.OffscreenBuffer.setZoomLevelsFromOption((Core.TileScale == 2) ? Core.OptionZoomLevels2x : Core.OptionZoomLevels1x);
            this.OffscreenBuffer.create(Display.getWidth(), Display.getHeight());
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void init(final int width, final int height, final int offscreenwidth, final int offscreenheight, final Canvas canvas, final Canvas full) throws LWJGLException {
        Core.width = width;
        Core.height = height;
        if (height > 768 && this.supportsFBO() && this.OffscreenBuffer == null) {
            Core.bDoubleSize = true;
        }
        Core.canvas = canvas;
        Core.fullscreencanvas = full;
        Display.setVSyncEnabled(false);
        GL11.glEnable(3553);
        GL11.glTexEnvf(8960, 8704, 8448.0f);
        IndieGL.glBlendFunc(770, 771);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.sharedInit();
    }
    
    public void setScreenSize(final int width, final int height) {
        if (Core.width != width || height != Core.height) {
            final int oldWidth = Core.width;
            final int oldHeight = Core.height;
            DebugLog.log("Screen resolution changed from " + oldWidth + "x" + oldHeight + " to " + width + "x" + height + " fullScreen=" + Core.fullScreen);
            Core.width = width;
            Core.height = height;
            if (this.OffscreenBuffer != null && this.OffscreenBuffer.Current != null) {
                this.OffscreenBuffer.destroy();
                try {
                    this.OffscreenBuffer.setZoomLevelsFromOption((Core.TileScale == 2) ? Core.OptionZoomLevels2x : Core.OptionZoomLevels1x);
                    this.OffscreenBuffer.create(width, height);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                LuaEventManager.triggerEvent("OnResolutionChange", oldWidth, oldHeight, width, height);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setForceScreenSize() {
    }
    
    public static boolean supportCompressedTextures() {
        return GLContext.getCapabilities().GL_EXT_texture_compression_latc;
    }
    
    public void refreshOffscreen() {
    }
    
    public void StartFrame() {
        if (LuaManager.thread != null && LuaManager.thread.bStep) {
            return;
        }
        if (this.RenderShader != null && this.OffscreenBuffer.Current != null) {
            this.RenderShader.setTexture(this.OffscreenBuffer.getTexture(0));
        }
        SpriteRenderer.instance.preRender();
        UIManager.resize();
        final boolean PlayerTripping = false;
        TextureID.TextureIDStack.clear();
        Texture.BindCount = 0;
        if (!PlayerTripping) {
            IndieGL.glClear(18176);
        }
        if (this.OffscreenBuffer.Current != null) {
            SpriteRenderer.instance.glBuffer(1, 0);
        }
        IndieGL.glDoStartFrame(this.getOffscreenWidth(0), this.getOffscreenHeight(0), 0);
        if (Core.DoFiltering) {}
        this.frameStage = 1;
    }
    
    public void StartFrame(final int nPlayer, final boolean clear) {
        if (LuaManager.thread.bStep) {
            return;
        }
        this.OffscreenBuffer.update();
        if (this.RenderShader != null && this.OffscreenBuffer.Current != null) {
            this.RenderShader.setTexture(this.OffscreenBuffer.getTexture(nPlayer));
        }
        if (clear) {
            SpriteRenderer.instance.preRender();
        }
        final boolean PlayerTripping = false;
        TextureID.TextureIDStack.clear();
        Texture.BindCount = 0;
        IndieGL.glLoadIdentity();
        if (this.OffscreenBuffer.Current != null) {
            SpriteRenderer.instance.glBuffer(1, nPlayer);
        }
        if (clear) {
            IndieGL.glClear(17664);
        }
        IndieGL.glDoStartFrame(this.getOffscreenWidth(nPlayer), this.getOffscreenHeight(nPlayer), nPlayer);
        IndieGL.glClear(17664);
        this.frameStage = 1;
    }
    
    public TextureFBO getOffscreenBuffer() {
        return this.OffscreenBuffer.getCurrent(0);
    }
    
    public TextureFBO getOffscreenBuffer(final int nPlayer) {
        return this.OffscreenBuffer.getCurrent(nPlayer);
    }
    
    public void setLastRenderedFBO(final TextureFBO fbo) {
        this.OffscreenBuffer.FBOrendered = fbo;
    }
    
    public void DoStartFrameStuff(final int w, final int h, final int player) {
        this.DoStartFrameStuff(w, h, player, false);
    }
    
    public void DoStartFrameStuff(final int w, final int h, final int player, final boolean isTextFrame) {
        GL11.glEnable(3042);
        GL11.glDepthFunc(519);
        while (this.stack > 0) {
            GL11.glPopMatrix();
            GL11.glPopAttrib();
            this.stack -= 2;
        }
        GL11.glAlphaFunc(516, 0.0f);
        GL11.glPushAttrib(2048);
        ++this.stack;
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        ++this.stack;
        GL11.glLoadIdentity();
        GLU.gluOrtho2D(0.0f, (float)w, (float)h, 0.0f);
        GL11.glMatrixMode(5888);
        if (player != -1) {
            int ow;
            int oh;
            if (isTextFrame) {
                ow = w;
                oh = h;
            }
            else {
                ow = this.getOffscreenTrueWidth();
                oh = this.getOffscreenTrueHeight();
                if (IsoPlayer.numPlayers > 1) {
                    ow /= 2;
                }
                if (IsoPlayer.numPlayers > 2) {
                    oh /= 2;
                }
            }
            float y = 0.0f;
            final float x = ow * (player % 2);
            if (player < 2 && IsoPlayer.numPlayers > 2) {
                y += oh;
            }
            GL11.glViewport((int)x, (int)y, w, h);
            GL11.glEnable(3089);
            GL11.glScissor((int)x, (int)y, w, h);
        }
        else {
            GL11.glViewport(0, 0, w, h);
        }
        GL11.glLoadIdentity();
    }
    
    public void PushIso() {
        SpriteRenderer.instance.PushIso();
    }
    
    public void PopIso() {
        SpriteRenderer.instance.PopIso();
    }
    
    public void DoPushIsoStuff() {
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        final float dist = 10.0f;
        final float x = IsoCamera.CamCharacter.getX();
        final float y = IsoCamera.CamCharacter.getY();
        final float z = IsoCamera.CamCharacter.getZ();
        GL11.glOrtho(100.0, -100.0, -100.0, 100.0, -500.0, 500.0);
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glRotatef(25.264f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(225.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(80.0f, 0.0f, -80.0f);
        GL11.glScalef(110.0f, 110.0f, -110.0f);
    }
    
    public void DoPopIsoStuff() {
        GL11.glDepthFunc(519);
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
    }
    
    public void DoEndFrameStuff(final int w, final int h) {
        GL11.glPopAttrib();
        --this.stack;
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        --this.stack;
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glDisable(3089);
    }
    
    public void RenderOffScreenBuffer() {
        if (LuaManager.thread != null && LuaManager.thread.bStep) {
            return;
        }
        if (this.OffscreenBuffer.Current == null) {
            return;
        }
        IndieGL.disableStencilTest();
        IndieGL.glDoStartFrame(Core.width, Core.height, -1);
        IndieGL.glDisable(3042);
        this.OffscreenBuffer.render();
        IndieGL.glDoEndFrame();
    }
    
    public void StartFrameText(final int nPlayer) {
        if (LuaManager.thread != null && LuaManager.thread.bStep) {
            return;
        }
        IndieGL.glDoStartFrame(IsoCamera.getScreenWidth(nPlayer), IsoCamera.getScreenHeight(nPlayer), nPlayer, true);
        IndieGL.glTexParameteri(3553, 10241, 9729);
        IndieGL.glTexParameteri(3553, 10240, 9728);
        this.frameStage = 2;
    }
    
    public void StartFrameUI() {
        if (LuaManager.thread != null && LuaManager.thread.bStep) {
            return;
        }
        IndieGL.glDoStartFrame(Core.width, Core.height, -1);
        IndieGL.glClear(1024);
        UIManager.resize();
        IndieGL.glTexParameteri(3553, 10241, 9729);
        IndieGL.glTexParameteri(3553, 10240, 9728);
        this.frameStage = 3;
    }
    
    public void StartFrameUIOld() {
        if (LuaManager.thread != null && LuaManager.thread.bStep) {
            return;
        }
        if (this.OffscreenBuffer.Current != null) {
            IndieGL.disableStencilTest();
        }
        if (this.OffscreenBuffer.Current != null) {
            IndieGL.glClear(17664);
        }
        IndieGL.glTexParameteri(3553, 10241, 9729);
        IndieGL.glTexParameteri(3553, 10240, 9728);
        IndieGL.glDoStartFrame(Core.width, Core.height, -1);
        if (Core.DoFiltering) {}
        UIManager.resize();
        this.OffscreenBuffer.render();
        IndieGL.glTexParameteri(3553, 10241, 9729);
        IndieGL.glTexParameteri(3553, 10240, 9728);
        this.frameStage = 3;
    }
    
    public Map<String, Integer> getKeyMaps() {
        return this.keyMaps;
    }
    
    public void setKeyMaps(final Map<String, Integer> keyMaps) {
        this.keyMaps = keyMaps;
    }
    
    public void reinitKeyMaps() {
        this.keyMaps = new HashMap<String, Integer>();
    }
    
    public int getKey(final String keyName) {
        if (this.keyMaps == null) {
            return 0;
        }
        if (this.keyMaps.get(keyName) != null) {
            return this.keyMaps.get(keyName);
        }
        return 0;
    }
    
    public void addKeyBinding(final String keyName, final Integer key) {
        if (this.keyMaps == null) {
            this.keyMaps = new HashMap<String, Integer>();
        }
        this.keyMaps.put(keyName, key);
    }
    
    public static boolean isLastStand() {
        return Core.bLastStand;
    }
    
    public String getVersionNumber() {
        return this.versionNumber;
    }
    
    public String getSteamServerVersion() {
        return this.steamServerVersion;
    }
    
    public void DoFrameReady() {
        if (GameWindow.OSValidator.isMac()) {
            if (Mouse.isCreated()) {
                Mouse.poll();
                Mouse.updateCursor();
            }
            if (Keyboard.isCreated()) {
                Keyboard.poll();
            }
            if (Controllers.isCreated()) {
                Controllers.poll();
            }
        }
        else {
            Display.processMessages();
        }
        getInstance().updateKeyboard();
    }
    
    public float getZoom(final int playerIndex) {
        if (this.OffscreenBuffer != null && this.OffscreenBuffer.Current != null) {
            return this.OffscreenBuffer.zoom[playerIndex];
        }
        return 1.0f;
    }
    
    public float getNextZoom(final int playerIndex, final int del) {
        if (this.OffscreenBuffer != null) {
            return this.OffscreenBuffer.getNextZoom(playerIndex, del);
        }
        return 1.0f;
    }
    
    public float getMinZoom() {
        if (this.OffscreenBuffer != null) {
            return this.OffscreenBuffer.getMinZoom();
        }
        return 1.0f;
    }
    
    public float getMaxZoom() {
        if (this.OffscreenBuffer != null) {
            return this.OffscreenBuffer.getMaxZoom();
        }
        return 1.0f;
    }
    
    public void doZoomScroll(final int playerIndex, final int del) {
        if (this.OffscreenBuffer != null) {
            this.OffscreenBuffer.doZoomScroll(playerIndex, del);
        }
    }
    
    public String getSaveFolder() {
        return this.saveFolder;
    }
    
    public void setSaveFolder(final String newSaveFolder) {
        if (!this.saveFolder.equals(newSaveFolder)) {
            File newDir = new File(newSaveFolder);
            if (!newDir.exists()) {
                newDir.mkdir();
            }
            newDir = new File(newSaveFolder + File.separator + "mods");
            if (!newDir.exists()) {
                newDir.mkdir();
            }
            final String to = this.saveFolder + File.separator;
            this.saveFolder = newSaveFolder;
            this.copyPasteFolders(to + "mods");
            this.deleteDirectoryRecusrively(to);
        }
    }
    
    public void deleteDirectoryRecusrively(final String directory) {
        final File toRemove = new File(directory);
        final String[] internalNames = toRemove.list();
        for (int i = 0; i < internalNames.length; ++i) {
            final File fileDelete = new File(directory + File.separator + internalNames[i]);
            if (fileDelete.isDirectory()) {
                this.deleteDirectoryRecusrively(directory + File.separator + internalNames[i]);
            }
            else {
                fileDelete.delete();
            }
        }
        toRemove.delete();
    }
    
    public boolean getOptionZoom() {
        return Core.OptionZoom;
    }
    
    public void setOptionZoom(final boolean zoom) {
        Core.OptionZoom = zoom;
    }
    
    public void zoomOptionChanged(final boolean inGame) {
        if (!inGame) {
            Core.SafeMode = Core.SafeModeForced;
            this.OffscreenBuffer.bZoomEnabled = (Core.OptionZoom && !Core.SafeModeForced);
            return;
        }
        RenderThread.borrowContext();
        if (!Core.OptionZoom || Core.SafeModeForced) {
            this.OffscreenBuffer.destroy();
            Core.SafeMode = true;
            this.bSupportsFBO = false;
            this.OffscreenBuffer.bZoomEnabled = false;
        }
        else {
            Core.SafeMode = false;
            this.bSupportsFBO = true;
            this.OffscreenBuffer.bZoomEnabled = true;
            this.supportsFBO();
        }
        RenderThread.returnContext();
        DebugLog.log("SafeMode is " + (Core.SafeMode ? "on" : "off"));
    }
    
    public void zoomLevelsChanged() {
        if (this.OffscreenBuffer.Current != null) {
            RenderThread.borrowContext();
            this.OffscreenBuffer.destroy();
            this.zoomOptionChanged(true);
            RenderThread.returnContext();
        }
    }
    
    public boolean isZoomEnabled() {
        return this.OffscreenBuffer.bZoomEnabled;
    }
    
    public void initFBOs() {
        if (!Core.OptionZoom || Core.SafeModeForced) {
            Core.SafeMode = true;
            this.OffscreenBuffer.bZoomEnabled = false;
        }
        else {
            RenderThread.borrowContext();
            this.supportsFBO();
            RenderThread.returnContext();
        }
        DebugLog.log("SafeMode is " + (Core.SafeMode ? "on" : "off"));
    }
    
    public boolean getAutoZoom(final int playerIndex) {
        return Core.bAutoZoom[playerIndex];
    }
    
    public void setAutoZoom(final int playerIndex, final boolean auto) {
        Core.bAutoZoom[playerIndex] = auto;
        if (this.OffscreenBuffer != null) {
            this.OffscreenBuffer.bAutoZoom[playerIndex] = auto;
        }
    }
    
    public boolean getOptionVSync() {
        return Core.OptionVSync;
    }
    
    public void setOptionVSync(final boolean sync) {
        Core.OptionVSync = sync;
        RenderThread.borrowContext();
        Display.setVSyncEnabled(sync);
        RenderThread.returnContext();
    }
    
    public int getOptionSoundVolume() {
        return Core.OptionSoundVolume;
    }
    
    public float getRealOptionSoundVolume() {
        return Core.OptionSoundVolume / 10.0f;
    }
    
    public void setOptionSoundVolume(final int volume) {
        Core.OptionSoundVolume = Math.max(0, Math.min(10, volume));
        if (SoundManager.instance != null) {
            SoundManager.instance.setSoundVolume(volume / 10.0f);
        }
    }
    
    public int getOptionMusicVolume() {
        return Core.OptionMusicVolume;
    }
    
    public void setOptionMusicVolume(final int volume) {
        Core.OptionMusicVolume = Math.max(0, Math.min(10, volume));
        if (SoundManager.instance != null) {
            SoundManager.instance.setMusicVolume(volume / 10.0f);
        }
    }
    
    public int getOptionAmbientVolume() {
        return Core.OptionAmbientVolume;
    }
    
    public void setOptionAmbientVolume(final int volume) {
        Core.OptionAmbientVolume = Math.max(0, Math.min(10, volume));
        if (SoundManager.instance != null) {
            SoundManager.instance.setAmbientVolume(volume / 10.0f);
        }
    }
    
    public int getOptionMusicLibrary() {
        return Core.OptionMusicLibrary;
    }
    
    public void setOptionMusicLibrary(int m) {
        if (m < 1) {
            m = 1;
        }
        if (m > 3) {
            m = 3;
        }
        Core.OptionMusicLibrary = m;
    }
    
    public int getOptionFliesVolume() {
        return Core.OptionFliesVolume;
    }
    
    public void setOptionFliesVolume(final int volume) {
        Core.OptionFliesVolume = Math.max(0, Math.min(10, volume));
    }
    
    public int getOptionHeartVolume() {
        return Core.OptionHeartVolume;
    }
    
    public void setOptionHeartVolume(final int volume) {
        Core.OptionHeartVolume = Math.max(0, Math.min(10, volume));
    }
    
    public boolean getOptionVoiceEnable() {
        return Core.OptionVoiceEnable;
    }
    
    public void setOptionVoiceEnable(final boolean option) {
        Core.OptionVoiceEnable = option;
    }
    
    public int getOptionVoiceMode() {
        return Core.OptionVoiceMode;
    }
    
    public void setOptionVoiceMode(final int option) {
        Core.OptionVoiceMode = option;
        VoiceManager.instance.setMode(option);
    }
    
    public int getOptionVoiceVADMode() {
        return Core.OptionVoiceVADMode;
    }
    
    public void setOptionVoiceVADMode(final int option) {
        Core.OptionVoiceVADMode = option;
        VoiceManager.instance.setVADMode(option);
    }
    
    public int getOptionVoiceVolumeMic() {
        return Core.OptionVoiceVolumeMic;
    }
    
    public void setOptionVoiceVolumeMic(final int option) {
        Core.OptionVoiceVolumeMic = option;
        VoiceManager.instance.setVolumeMic(option);
    }
    
    public int getOptionVoiceVolumePlayers() {
        return Core.OptionVoiceVolumePlayers;
    }
    
    public void setOptionVoiceVolumePlayers(final int option) {
        Core.OptionVoiceVolumePlayers = option;
        VoiceManager.instance.setVolumePlayers(option);
    }
    
    public String getOptionVoiceRecordDeviceName() {
        return Core.OptionVoiceRecordDeviceName;
    }
    
    public void setOptionVoiceRecordDeviceName(final String option) {
        Core.OptionVoiceRecordDeviceName = option;
        VoiceManager.instance.UpdateRecordDevice();
    }
    
    public int getOptionVoiceRecordDevice() {
        if (Core.SoundDisabled) {
            return 0;
        }
        for (int num_devices = javafmod.FMOD_System_GetRecordNumDrivers(), i = 0; i < num_devices; ++i) {
            final FMOD_DriverInfo info = new FMOD_DriverInfo();
            javafmod.FMOD_System_GetRecordDriverInfo(i, info);
            if (info.name.equals(Core.OptionVoiceRecordDeviceName)) {
                return i + 1;
            }
        }
        return 0;
    }
    
    public void setOptionVoiceRecordDevice(final int option) {
        if (Core.SoundDisabled) {
            return;
        }
        if (option < 1) {
            return;
        }
        final FMOD_DriverInfo info = new FMOD_DriverInfo();
        javafmod.FMOD_System_GetRecordDriverInfo(option - 1, info);
        Core.OptionVoiceRecordDeviceName = info.name;
        VoiceManager.instance.UpdateRecordDevice();
    }
    
    public int getMicVolumeIndicator() {
        return VoiceManager.instance.getMicVolumeIndicator();
    }
    
    public boolean getMicVolumeError() {
        return VoiceManager.instance.getMicVolumeError();
    }
    
    public boolean getServerVOIPEnable() {
        return VoiceManager.instance.getServerVOIPEnable();
    }
    
    public int getOptionReloadDifficulty() {
        return Core.OptionReloadDifficulty;
    }
    
    public void setOptionReloadDifficulty(final int d) {
        Core.OptionReloadDifficulty = Math.max(1, Math.min(3, d));
    }
    
    public boolean getOptionRackProgress() {
        return Core.OptionRackProgress;
    }
    
    public void setOptionRackProgress(final boolean b) {
        Core.OptionRackProgress = b;
    }
    
    public String getOptionInventoryFont() {
        return Core.OptionInventoryFont;
    }
    
    public void setOptionInventoryFont(final String font) {
        Core.OptionInventoryFont = font;
    }
    
    public String getOptionMeasurementFormat() {
        return Core.OptionMeasurementFormat;
    }
    
    public void setOptionMeasurementFormat(final String format) {
        Core.OptionMeasurementFormat = format;
    }
    
    public int getOptionClockFormat() {
        return Core.OptionClockFormat;
    }
    
    public void setOptionClockFormat(int fmt) {
        if (fmt < 1) {
            fmt = 1;
        }
        if (fmt > 2) {
            fmt = 2;
        }
        Core.OptionClockFormat = fmt;
    }
    
    public boolean getOptionClock24Hour() {
        return Core.OptionClock24Hour;
    }
    
    public void setOptionClock24Hour(final boolean b24Hour) {
        Core.OptionClock24Hour = b24Hour;
    }
    
    public boolean getOptionModsEnabled() {
        return Core.OptionModsEnabled;
    }
    
    public void setOptionModsEnabled(final boolean enabled) {
        Core.OptionModsEnabled = enabled;
    }
    
    public int getOptionBloodDecals() {
        return Core.OptionBloodDecals;
    }
    
    public void setOptionBloodDecals(int n) {
        if (n < 0) {
            n = 0;
        }
        if (n > 10) {
            n = 10;
        }
        Core.OptionBloodDecals = n;
    }
    
    public boolean getOptionBorderlessWindow() {
        return Core.OptionBorderlessWindow;
    }
    
    public void setOptionBorderlessWindow(final boolean b) {
        Core.OptionBorderlessWindow = b;
    }
    
    public boolean getOptionTextureCompression() {
        return Core.OptionTextureCompression;
    }
    
    public void setOptionTextureCompression(final boolean b) {
        Core.OptionTextureCompression = b;
    }
    
    public boolean getOptionTexture2x() {
        return Core.OptionTexture2x;
    }
    
    public void setOptionTexture2x(final boolean b) {
        Core.OptionTexture2x = b;
    }
    
    public String getOptionZoomLevels1x() {
        return Core.OptionZoomLevels1x;
    }
    
    public void setOptionZoomLevels1x(final String levels) {
        Core.OptionZoomLevels1x = ((levels == null) ? "" : levels);
    }
    
    public String getOptionZoomLevels2x() {
        return Core.OptionZoomLevels2x;
    }
    
    public void setOptionZoomLevels2x(final String levels) {
        Core.OptionZoomLevels2x = ((levels == null) ? "" : levels);
    }
    
    public ArrayList<Integer> getDefaultZoomLevels() {
        return this.OffscreenBuffer.getDefaultZoomLevels();
    }
    
    public void setOptionActiveController(final int controllerIndex, final boolean active) {
        if (controllerIndex < 0 || controllerIndex >= GameWindow.GameInput.getControllerCount()) {
            return;
        }
        final Controller ctrlr = GameWindow.GameInput.getController(controllerIndex);
        if (ctrlr != null) {
            JoypadManager.instance.setControllerActive(ctrlr.getName(), active);
        }
    }
    
    public boolean getOptionActiveController(final String name) {
        return JoypadManager.instance.ActiveControllerNames.contains(name);
    }
    
    public void ResetLua(final boolean sp, final String reason) {
        if (SpriteRenderer.instance != null) {
            GameWindow.DrawReloadingLua = true;
            GameWindow.render();
            GameWindow.DrawReloadingLua = false;
        }
        ScriptManager.instance.Reset();
        LuaEventManager.Reset();
        UIManager.init();
        ScriptManager.instance.Reset();
        SurvivorFactory.Reset();
        ProfessionFactory.Reset();
        TraitFactory.Reset();
        ChooseGameInfo.Reset();
        LuaHookManager.Reset();
        LuaManager.init();
        JoypadManager.instance.Reset();
        GameKeyboard.doLuaKeyPressed = true;
        ZomboidFileSystem.instance.Reset();
        System.gc();
        ZomboidFileSystem.instance.init();
        ZomboidFileSystem.instance.loadMods();
        Translator.loadFiles();
        ScriptManager.instance.Load();
        try {
            LuaManager.LoadDirBase();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            GameWindow.DoLoadingText("Reloading Lua - ERRORS!");
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
        }
        ZomboidGlobals.Load();
        LuaEventManager.triggerEvent("OnGameBoot");
        LuaEventManager.triggerEvent("OnMainMenuEnter");
        LuaEventManager.triggerEvent("OnResetLua", reason);
    }
    
    public boolean isShowPing() {
        return this.showPing;
    }
    
    public void setShowPing(final boolean showPing) {
        this.showPing = showPing;
    }
    
    public boolean isForceSnow() {
        return this.forceSnow;
    }
    
    public void setForceSnow(final boolean forceSnow) {
        this.forceSnow = forceSnow;
    }
    
    public boolean isZombieGroupSound() {
        return this.zombieGroupSound;
    }
    
    public void setZombieGroupSound(final boolean zombieGroupSound) {
        this.zombieGroupSound = zombieGroupSound;
    }
    
    public String getBlinkingMoodle() {
        return this.blinkingMoodle;
    }
    
    public void setBlinkingMoodle(final String blinkingMoodle) {
        this.blinkingMoodle = blinkingMoodle;
    }
    
    public boolean isTutorialDone() {
        return this.tutorialDone;
    }
    
    public void setTutorialDone(final boolean done) {
        this.tutorialDone = done;
    }
    
    public void initPoisonousBerry() {
        final ArrayList<String> berriesList = new ArrayList<String>();
        berriesList.add("Base.BerryGeneric1");
        berriesList.add("Base.BerryGeneric2");
        berriesList.add("Base.BerryGeneric3");
        berriesList.add("Base.BerryGeneric4");
        berriesList.add("Base.BerryGeneric5");
        berriesList.add("Base.BerryPoisonIvy");
        this.setPoisonousBerry(berriesList.get(Rand.Next(0, berriesList.size() - 1)));
    }
    
    public void initPoisonousMushroom() {
        final ArrayList<String> mushroomList = new ArrayList<String>();
        mushroomList.add("Base.MushroomGeneric1");
        mushroomList.add("Base.MushroomGeneric2");
        mushroomList.add("Base.MushroomGeneric3");
        mushroomList.add("Base.MushroomGeneric4");
        mushroomList.add("Base.MushroomGeneric5");
        mushroomList.add("Base.MushroomGeneric6");
        mushroomList.add("Base.MushroomGeneric7");
        this.setPoisonousMushroom(mushroomList.get(Rand.Next(0, mushroomList.size() - 1)));
    }
    
    public String getPoisonousBerry() {
        return this.poisonousBerry;
    }
    
    public void setPoisonousBerry(final String poisonousBerry) {
        this.poisonousBerry = poisonousBerry;
    }
    
    public String getPoisonousMushroom() {
        return this.poisonousMushroom;
    }
    
    public void setPoisonousMushroom(final String poisonousMushroom) {
        this.poisonousMushroom = poisonousMushroom;
    }
    
    public static String getDifficulty() {
        return Core.difficulty;
    }
    
    public static void setDifficulty(final String vdifficulty) {
        Core.difficulty = vdifficulty;
    }
    
    public boolean isDoneNewSaveFolder() {
        return this.doneNewSaveFolder;
    }
    
    public void setDoneNewSaveFolder(final boolean doneNewSaveFolder) {
        this.doneNewSaveFolder = doneNewSaveFolder;
    }
    
    public static int getTileScale() {
        return Core.TileScale;
    }
    
    public boolean isInInventory() {
        return this.inInventory;
    }
    
    public void setInInventory(final boolean inInventory) {
        this.inInventory = inInventory;
    }
    
    public boolean isSelectingAll() {
        return this.isSelectingAll;
    }
    
    public void setIsSelectingAll(final boolean isSelectingAll) {
        this.isSelectingAll = isSelectingAll;
    }
    
    public boolean getContentTranslationsEnabled() {
        return Core.OptionEnableContentTranslations;
    }
    
    public void setContentTranslationsEnabled(final boolean b) {
        Core.OptionEnableContentTranslations = b;
    }
    
    public boolean isShowYourUsername() {
        return this.showYourUsername;
    }
    
    public void setShowYourUsername(final boolean showYourUsername) {
        this.showYourUsername = showYourUsername;
    }
    
    public ColorInfo getMpTextColor() {
        if (this.mpTextColor == null) {
            this.mpTextColor = new ColorInfo(Rand.Next(135) + 120, Rand.Next(135) + 120, Rand.Next(135) + 120, 255.0f);
        }
        return this.mpTextColor;
    }
    
    public void setMpTextColor(final ColorInfo mpTextColor) {
        if (mpTextColor.r < 0.19f) {
            mpTextColor.r = 0.19f;
        }
        if (mpTextColor.g < 0.19f) {
            mpTextColor.g = 0.19f;
        }
        if (mpTextColor.b < 0.19f) {
            mpTextColor.b = 0.19f;
        }
        this.mpTextColor = mpTextColor;
    }
    
    public boolean isAzerty() {
        return this.isAzerty;
    }
    
    public void setAzerty(final boolean isAzerty) {
        this.isAzerty = isAzerty;
    }
    
    public ColorInfo getObjectHighlitedColor() {
        return this.objectHighlitedColor;
    }
    
    public void setObjectHighlitedColor(final ColorInfo objectHighlitedColor) {
        this.objectHighlitedColor = objectHighlitedColor;
    }
    
    public String getSeenUpdateText() {
        return this.seenUpdateText;
    }
    
    public void setSeenUpdateText(final String seenUpdateText) {
        this.seenUpdateText = seenUpdateText;
    }
    
    public boolean isToggleToRun() {
        return this.toggleToRun;
    }
    
    public void setToggleToRun(final boolean toggleToRun) {
        this.toggleToRun = toggleToRun;
    }
    
    public int getXAngle(final int width, final float angle) {
        final double radian = Math.toRadians(225.0f + angle);
        final int x1 = (int)(Object)new Long(Math.round((Math.sqrt(2.0) * Math.cos(radian) + 1.0) * (width / 2)));
        return x1;
    }
    
    public int getYAngle(final int width, final float angle) {
        final double radian = Math.toRadians(225.0f + angle);
        final int y1 = (int)(Object)new Long(Math.round((Math.sqrt(2.0) * Math.sin(radian) + 1.0) * (width / 2)));
        return y1;
    }
    
    public boolean isCelsius() {
        return this.celsius;
    }
    
    public void setCelsius(final boolean celsius) {
        this.celsius = celsius;
    }
    
    public boolean isInDebug() {
        return Core.bDebug;
    }
    
    public boolean doWarnMapConflict() {
        return this.warnMapConflict;
    }
    
    public void setWarnMapConflict(final boolean doIt) {
        this.warnMapConflict = doIt;
    }
    
    public void setMapOrder(final LinkedList<String> order) {
        this.mapOrder = order;
    }
    
    public LinkedList<String> getMapOrder() {
        return this.mapOrder;
    }
    
    public boolean isRiversideDone() {
        return this.riversideDone;
    }
    
    public void setRiversideDone(final boolean riversideDone) {
        this.riversideDone = riversideDone;
    }
    
    static {
        Core.fakefullscreen = false;
        Core.SVN_REVISION = SVNRevision.init();
        Core.bMultithreadedRendering = true;
        Core.bDoubleSize = false;
        Core.bAltMoveMethod = false;
        Core.Zoom = 0.5f;
        Core.useLwjgl = true;
        Core.DoFiltering = false;
        Core.OptionZoom = true;
        Core.OptionModsEnabled = true;
        Core.OptionInventoryFont = "Medium";
        Core.OptionMeasurementFormat = "Metric";
        Core.OptionClockFormat = 1;
        Core.OptionClock24Hour = true;
        Core.OptionVSync = false;
        Core.OptionSoundVolume = 8;
        Core.OptionMusicVolume = 6;
        Core.OptionAmbientVolume = 5;
        Core.OptionMusicLibrary = 1;
        Core.OptionVoiceEnable = true;
        Core.OptionVoiceMode = 3;
        Core.OptionVoiceVADMode = 3;
        Core.OptionVoiceRecordDeviceName = "";
        Core.OptionVoiceVolumeMic = 10;
        Core.OptionVoiceVolumePlayers = 5;
        Core.OptionFliesVolume = 5;
        Core.OptionHeartVolume = 10;
        Core.OptionReloadDifficulty = 1;
        Core.OptionRackProgress = true;
        Core.OptionBloodDecals = 10;
        Core.OptionBorderlessWindow = false;
        Core.OptionTextureCompression = false;
        Core.OptionTexture2x = true;
        Core.OptionZoomLevels1x = "";
        Core.OptionZoomLevels2x = "";
        Core.OptionEnableContentTranslations = true;
        Core.difficulty = "Hardcore";
        Core.TileScale = 2;
        Core.width = 1280;
        Core.height = 720;
        Core.MaxJukeBoxesActive = 10;
        Core.NumJukeBoxesActive = 0;
        Core.GameMode = "Sandbox";
        Core.glMajorVersion = -1;
        Core.core = new Core();
        Core.bDebug = false;
        Core.bHighSqualityShadows = true;
        Core.CurrentTextEntryBox = null;
        Core.storyDirectory = "mods/";
        Core.modRootDirectory = "mods/media/";
        Core.fullScreen = false;
        Core.bAutoZoom = new boolean[4];
        Core.GameMap = "DEFAULT";
        Core.GameSaveWorld = "";
        Core.SafeMode = false;
        Core.SafeModeForced = false;
        Core.SoundDisabled = false;
        Core.bIsSteam = true;
        Core.bLastStand = false;
        Core.bLoadedWithMultithreaded = false;
        Core.bExiting = false;
    }
}

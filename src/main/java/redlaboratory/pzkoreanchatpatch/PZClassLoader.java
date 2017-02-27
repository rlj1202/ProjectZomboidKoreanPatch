package redlaboratory.pzkoreanchatpatch;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PZClassLoader extends ClassLoader {
	
	private File pzFolder;
	private File[] libraries;
	
	private HashMap<String, Class<?>> cache;
	
	public PZClassLoader(File pzFolder) {
		super(PZClassLoader.class.getClassLoader());
		
		this.pzFolder = pzFolder;
		this.cache = new HashMap<String, Class<?>>();
		
		libraries = pzFolder.listFiles( new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.substring(name.lastIndexOf('.') + 1).equals("jar");
			}
		} );
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> result;
		
		if ((result = cache.get(name)) == null) {
			result = findClass(name);
			
			if (result != null) {
				cache.put(name, result);
				
				System.out.println("Load class from pzFolder: " + name);// XXX
			} else {
				if (!name.startsWith("zombie")) {
					result = super.loadClass(name);
					
					if (result != null) {
						cache.put(name, result);
						
						System.out.println("Load class from JVM: " + name);// XXX
					}
				}
			}
		}
		
		return result;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes;
		Class<?> result;
		
		try {
			byte[] buffer = new byte[1024];
			int read;
			
			{
				File file = new File(pzFolder, name.replace('.', File.separatorChar).concat(".class"));
				
				if (file.exists()) {
					FileInputStream fis = new FileInputStream(file);
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					
					while (fis.available() > 0) {
						read = fis.read(buffer, 0, buffer.length);
						byteStream.write(buffer, 0, read);
					}
					
					fis.close();
					
					classBytes = byteStream.toByteArray();
					
					result = defineClass(name, classBytes, 0, classBytes.length, null);
					
					return result;					
				}
			} 
			
			if (libraries != null) {
				for (File libJar : libraries) {
					JarFile jar = new JarFile(libJar);
					JarEntry entry = jar.getJarEntry(name.replace('.', '/').concat(".class"));
					
					if (entry == null) continue;
					
					InputStream is = jar.getInputStream(entry);
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					
					while (is.available() > 0) {
						read = is.read(buffer, 0, buffer.length);
						byteStream.write(buffer, 0, read);
					}
					
					jar.close();
					
					classBytes = byteStream.toByteArray();
					
					result = defineClass(name, classBytes, 0, classBytes.length, null);
					
					return result;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}

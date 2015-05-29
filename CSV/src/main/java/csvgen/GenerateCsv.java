package csvgen;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;
 
public class GenerateCsv{

   public static void main(String [] args){
   
	    String outFile="G:/JAVA/GoogleCloud/CSV/Mock-load-data.csv";
		GenerateCsv gencsv = new GenerateCsv();
		
		try{
			FileWriter writer = new FileWriter(outFile);
			   
			for(int rec = 1; rec < 1000; rec++){
				long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
				long end = Timestamp.valueOf("2015-05-28 00:00:00").getTime();
				long diff = end - offset + 1;
				gencsv.appendCSV(rec, offset, diff, gencsv, writer);
	    	}
	     
			writer.flush();
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		} 
   }
   
   public String getSessionId(){
		String content = "0123456789ABCDEXYZFJOLQP";
		int N = content.length();
		Random r = new Random();
		String sid = "";
		
		for (int i = 0; i < 10; i++) {
			sid += content.charAt(r.nextInt(N));
		}
		
		return sid;
   }
   
   public String getName(){
		String[] names = { "bowlermedian","coronetenter","peskythermal","pumpkinidentity","quinticactinium","extremesediment","classroomcorrodes","mergecobol","fardset","kenyanlock","kissingcanoodle","loudmouthchocolat","goldturkey","cepheushills","arrowpolecats","minorslave","pointinghertz","parcelproof","ultimatemodulus","phobicpacific","sanidinecrohn","mistyfetus","noteworthybewildered","liquidcaelum","versedagenda","lustrouscluster","bowlbeep","earthlatitude","adidascongolese","pizzlepsi","ascensiontaut","neighlollygag","blackopen","nuggetstirrer","multipackapus","weightsharing","pompresigned","pitchershocked","wideeyedfield","overtuneven","costlyamoeba","afocalsquare","oryxdugout","barstone","inclinedtong","ibisdingdong","twitchspotty","constantcritical","heavyliving","modelcloudy","deformedbaa","excitablesudanese","oppositethank","wackyaustralis","bakerysin","leancapped","staticwind","soundtrill","substancepratfall","snazzynonchalant","kitsliving","viciouscracking","paperbrood","chirmalbedo","driftmatter","listendeneb","foxbasestollen","messywire","pikeform","pectoralsided","samedevelop","quarksour","mercedesthigh","clublawful","colorfulspin","halfthe","maternalelliptical","handnuclear","strikeblighter","devilbetrayed","quadratvolt","alphadrunk","movechoking","peevishadoption","hungryaspiring","chockyouthful","paringqualling","revealflight","ligamentvenogram","volleyhealthy","arrestbar","mangotimid","moderatoaggressive","paradigmpuppy","flanbull","valuewilling","receiveserve","tricklecover","cornybunch","rufouscrane","holmiumgadget","sanepride","claysnooze","glomnutty","yorkshirepastern","hawkpolling","ethanoatebetelgeuse","acneclay","irascibleardent","dilationgrist","grumblingtriggers","biotablazing","towerfishified","ardentsting","transportbulb","spacetimechimps","coyotecenters","trousershen","foamordered","employsmarties","hollandaisefolding","announcedown","basearray","puffyirons","gardenhockey","factorjelly","ibericocathode","implodeski","allianzburet","pointphysical","growthwaist","ajartendency","ribbityearling","luxuriousprofess","crazysee","winddizzy","scotlandslips","factorlost","yeastpaddling","torchshiny","weightcasting","baillondna","lodgerods","farrowacrobat","squarehat","paringtime","apogeecatenary","precedea","lsrlhopes","norththese","segnomoisture","digitsnewton","ellipsebrand","gamesneak","vacuumremuda","pleurisyrawchicken","cloudslarge","broccolismuth","yellegad","spatialscrawny","combustiblefeline","membermetal","glozingrippling","lawfulgamma","relyavaricious","fagsstash","cocklefox","arborealabuse","ridgeaxle","spotbase","apigreedy","liftnitrogen","unitvalence","biomassorphean","raingerman","programoil","flusteredtraits","reversemackerel","degreeweedy","liquiddarling","pinesecond","scaletoffee","induscrevasse","emeraldtuna","webflogging","mobdistracted","troupecoughs","equationcharge","pintailring","plumberdray","breederbouffant","pulleysecretive","modelvivace","firestring","tatinequation","sawtpine","untidyoyster","chimpsnothing","rockinggamy","kelvinoryx" };
		
		return names[(int) (Math.random() * names.length)];
   }
   
   public String getBrowser(){
		String[] browsers  = { "Internet Explorer","Firefox","Chrome","Safari","Opera" };
		
		return browsers[(int) (Math.random() * browsers.length)];
   }
   
   public String getDevice(){
		String[] device= { "PC", "Laptop", "Phone"  };
		
		return device[(int) (Math.random() * device.length)];
	}
	
	public String getScreen(){
		String[] screen= { "1366×768","1024×768", "1280x800", "320x480" };
		
		return screen[(int) (Math.random() * screen.length)];
	}
	
	public String getLocation(){
		String[] location={ "Chennai", "Delhi", "Pondicherry", "Bangalore", "Coimbatore" };
		
		return location[(int) (Math.random() * location.length)];
	}
	
	public String getCatProduct(){
		String[] catProducts = { "Books:A Partial History of Lost Causes ","Books:Amelia Anne Is Dead and Gone ","Books:And When She Was Good ","Books:Billy Lynn's Long Halftime Walk  ","Books:Don't Ever Get Old ","Books:Every Love Story Is a Ghost Story ","Books:Happiness Is a Chemical in the Brain ","Books:HHhH by Laurent Binet","Books:Let's Pretend This Never Happened ","Books:Lots of Candles, Plenty of Cake ","Books:New Ways to Kill Your Mother","Books:No One is Here Except All of Us ","Books:Red Ruby Heart in a Cold Blue Sea ","Books:Say Nice Things About Detroit ","Books:Tell the Wolves I'm Home ","Books:The End of Your Life Book Club ","Books:The Liar, the Bitch and the Wardrobe ","Books:The People of Forever Are Not Afraid ","Books:There Is No Dog ","Books:This Book Is Full of Spiders: Seriously, Dude, Don't Touch It ","Books:This Is How You Lose Her ","Books:What We Talk About When We Talk About Anne Frank ","Books:Where'd You Go, Bernadette ","Books:Why Be Happy When You Could Be Normal? ","Books:Would You Eat Your Cat? ","Phones:Lenovo A7000","Phones:Micromax Canvas Spark","Phones:Micromax Canvas Nitro A311","Phones:Samsung Galaxy Core Prime","Phones:Xiaomi Redmi Note 4G","Phones:Lava Iris X8","Phones:Micromax Canvas Juice 2","Phones:Lenovo A6000","Phones:Intex Aqua Power HD","Phones:Samsung Galaxy Core 2 Duos","Movies:The Shawshank Redemption (1994)","Movies:The Godfather (1972)","Movies:The Godfather: Part II (1974)","Movies:Pulp Fiction (1994)","Movies:The Good, the Bad and the Ugly (1966)","Movies:Fight Club (1999) ","Movies:Forrest Gump (1994)","Movies:Memento (2000)","Movies:Apocalypse Now (1979)","Movies:Whiplash (2014)","Movies:The Intouchables (2011)","Movies:The Pianist (2002)","Movies:Sunset Blvd. (1950)","Movies:Gladiator (2000)","Movies:American Beauty (1999)","Laptops:Apple MacBook Air 13-Inch ","Laptops:Apple MacBook Pro 15-Inch Retina Display ","Laptops:Dell Inspiron 17 5000 Series Non-Touch","Laptops:Dell Latitude 14 7000 Series (E7450)","Laptops:HP Spectre x360 13t (13-4003)","Laptops:HP Stream 13 (13-c020nr)","Laptops:Origin EON15-X","Laptops:Razer Blade ","Laptops:Toshiba Tecra C50-B1503","Laptops:Apple MacBook ","Tablets:Samsung Galaxy Tab 2 10.1","Tablets:Asus Transformer Pad Infinity","Tablets:Samsung Galaxy Note 8.0","Tablets:Amazon Kindle Fire HDX 8.9","Tablets:Tesco Hudl","Tablets:Google Nexus 7","Tablets:iPad mini","Tablets:Amazon Kindle Fire HDX 7","Tablets:Samsung Galaxy Note Pro 12.2","Tablets:Microsoft Surface Pro 3" }; 
		
		return catProducts[(int) (Math.random() * catProducts.length)];
	}
 
   private static void generateCsvFile(String sFileName)
   {
	
    }

	private static void appendCSV(int pid, long offset, long diff, GenerateCsv gencsv, FileWriter writer) throws IOException {
			
		String username = gencsv.getName();
		int sessionNum = gencsv.randInt(5,15);
		int viewNum = gencsv.randInt(2,10);
		int sec = 600;
				
		for(int cnt=1; cnt < sessionNum; cnt++){
			String sessionId = gencsv.getSessionId();
			String browser = gencsv.getBrowser();
			String device = gencsv.getDevice();
			String Screen = gencsv.getScreen();
			String location = gencsv.getLocation();	
			Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
			
			for(int vcr=1; vcr < viewNum; vcr++){
				String[] catProduct = gencsv.getCatProduct().split(":");
				long off = Timestamp.valueOf(rand.toString()).getTime();
				rand = new Timestamp(off + (sec * 1000));
				
				writer.append(username);
				writer.append(',');
				writer.append(browser);
				writer.append(',');
				writer.append(device);
				writer.append(',');
				writer.append(Screen);
				writer.append(',');
				writer.append(location);
				writer.append(',');
				writer.append(catProduct[0]);
				writer.append(',');
				writer.append("\"" + catProduct[1] + "\"");
				writer.append(',');
				writer.append(sessionId);
				writer.append(',');
				writer.append("\"" + rand.toString() + "\"");
				writer.append('\n');
			}
		}
	}
	
	public int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	
}



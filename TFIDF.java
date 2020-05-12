
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TFIDF {

	public static void main(String args[])
	{
		try
		{
			/*Declaration of variables to be used*/
			Map<String, Integer> phase1 = new HashMap<>();
			Map<String, Integer> phase2 = new HashMap<>();
			Map<String, Integer> phase3 = new HashMap<>();
			Map<String, String> outMap = new HashMap<>();
			ArrayList<String> outList=new ArrayList<>(); 
			//ArrayList<String> sortedDataOrder=new ArrayList<>();
			
			//FileWriter writer=new FileWriter("output.txt");
			
			/* Reading the file of Phase1 and storing it in HashMap phase1*/
			File f1 = new File("C:\\Users\\Ankit Pandey\\Desktop\\BigData\\Program1\\Q1\\OP1\\Phase1.txt");
			FileReader reader = new FileReader(f1);
			BufferedReader br = new BufferedReader(reader);
			ArrayList<String> tempDataList=getDataFromFile(br);
			for(String data:tempDataList) {
				String[] s=data.split("\t");
					phase1.put(s[0], Integer.parseInt(s[1]));
			}
			
			/* Reading the file of Phase2 and storing it in HashMap phase2*/
			File f2 = new File("C:\\Users\\Ankit Pandey\\Desktop\\BigData\\Program1\\Q1\\OP1\\Phase2.txt");
			FileReader reader2 = new FileReader(f2);
			BufferedReader br2 = new BufferedReader(reader2);
			tempDataList=getDataFromFile(br2);
			for(String data:tempDataList) {
				String[] s=data.split("\t");
					phase2.put(s[0], Integer.parseInt(s[1]));
					//System.out.println(phase2);
			}
			
			//System.out.println(phase2);
			/* Reading the file of Phase3 and storing it in HashMap phase3*/
			File f3 = new File("C:\\Users\\Ankit Pandey\\Desktop\\BigData\\Program1\\Q1\\OP1\\Phase3.txt");
			FileReader reader3 = new FileReader(f3);
			BufferedReader br3 = new BufferedReader(reader3);
			tempDataList=getDataFromFile(br3);
			for(String data:tempDataList) {
				String[] s=data.split("\t");
					phase3.put(s[0], Integer.parseInt(s[1]));
			}
			
			//int count=0;
			
			
			for(String tempDataKey: phase1.keySet())
			{
				int totalDocs= phase2.size();
				//System.out.println("totalDocs"+totalDocs);
				//System.out.println(tempDataKey.split(",")[0]);
				int docWithTerm= phase3.get(tempDataKey.split(",")[0]);
				//System.out.println("totalDocs/docWithTerm:"+(double)totalDocs/docWithTerm);
				double totalBydoc=(double)totalDocs/(double)docWithTerm;
				double idf=Math.log10(totalBydoc);
				int tFrequency= phase1.get(tempDataKey);
				//System.out.println("tFrequency:"+tFrequency);
				//System.out.println("DocName:-"+tempDataKey.split(",")[1]);
				//System.out.println(phase2);
				
				int totalTerms=phase2.get(tempDataKey.split(",")[1]);
				//System.out.println("totalTerms:"+totalTerms);
				double tf=(double)tFrequency/totalTerms;
				//System.out.println("tf:"+tf);
				String tfIdf=String.format("%.12f", tf*idf);
				String invertedTempData=tempDataKey.split(",")[1]+","+tempDataKey.split(",")[0];
				outMap.put(invertedTempData, tfIdf);
				outList.add(invertedTempData);
				//String sortedData=tempDataKey.split(",")[1]+","+tfIdf+","+tempDataKey.split(",")[0];
				//sortedDataOrder.add(sortedData);

			}
			Collections.sort(outList);
			//List<String> words = new ArrayList<String>(); 
			//Collections.sort(sortedDocSetlist);
			//System.out.println(sortedDocSetlist);
			
			//Collections.sort(outList);
			
			/*for(String tempData:outList)
			{
				
				//System.out.println("Name:"+tempData.split(",")[1]+"\t\tDocName:"+tempData.split(",")[0]+"\t\tValue:"+outMap.get(tempData));
				//writer.write("Name:"+tempData.split(",")[1]+"\t\tDocName:"+tempData.split(",")[0]+"\t\tValue:"+outMap.get(tempData)+"\n");
			}*/
			//System.out.println("Size"+ohm.size());
			
			List<String> docTfIdf=new ArrayList<>();
			for(String tempData:outList)
			{
				docTfIdf.add(tempData.split(",")[0]+"_"+outMap.get(tempData)+"_"+tempData.split(",")[1]);
				
			}
			Collections.sort(docTfIdf,Collections.reverseOrder());
			System.out.println(docTfIdf);
		
			String tempDoc="";
			int count=0;
			
			
			
			FileWriter myWriter=new FileWriter("C:\\Users\\Ankit Pandey\\Desktop\\BigData\\Program1\\Q1\\OP1\\outputFile.txt");
			for(String s:docTfIdf)
			{
				if(!tempDoc.equals(s.split("_")[0]))
				{
					tempDoc=s.split("_")[0];
					count=0;
				}
				else if(tempDoc.equals(s.split("_")[0])&& count<18)
				{
					count++;
					//System.out.println("DocName: "+s.split("_")[0] +"\t \t TFIDF value: "+s.split("_")[1]+"\t \t Word: "+s.split("_")[2]);
					myWriter.write("DocName: "+s.split("_")[0] +"\t \t TFIDF value: "+s.split("_")[1]+"\t \t Word: "+s.split("_")[2]+"\n");
					
					
				}
			}
			//writer=new FileWriter("outputFile.txt");
			
			myWriter.close();

		}
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	static ArrayList<String> getDataFromFile(BufferedReader br) throws IOException {
		ArrayList<String> tempData=new ArrayList<>();
		String data = "";
		String s = br.readLine();
		System.out.println(s);
		while (s != null) {
			tempData.add(s);
			s = br.readLine();
		}
		return tempData;
	}
}

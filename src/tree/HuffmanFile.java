package tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

class Word
{
	private int count;
	private String value;
	public Word(int count,String value) {
		// TODO Auto-generated constructor stub
		this.value=value;
		this.count=count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

public class HuffmanFile {

	private static List<Term> list=new ArrayList<Term>();
	private static HashMap<String, Word> wordMap=new HashMap<>();
	private static List<Word> WordList=new LinkedList<>();
	public static void main(String[] args) {
		
		HuffmanFile file=new HuffmanFile();
		file.readFile("/home/hu/data/tree/huffmanfile");
		file.order();
	}
	public void split(String line)
	{
		list=ToAnalysis.parse(line);
		Iterator<Term> itor=list.iterator();
		String[] array=null;
		Word word=null;
		while(itor.hasNext())
		{
			String Text=itor.next().toString();
			//System.out.println(Text);
			array=Text.split("/");
			if(array.length>0)
			{
			if(wordMap.get(array[0])!=null)
			{
				word=wordMap.get(array[0]);
				int count=word.getCount();
				count++;
				word.setCount(count);
				wordMap.put(array[0], word);
			}
			else
			{
			word=new Word(1, array[0]);
			wordMap.put(array[0], word);
			}
			}
		}
		
	}

	public void readFile(String path)
	{
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String line=br.readLine();
			while(line!=null)
			{
				split(line);
				line=br.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void order()
	{
		Set<String> Wordset=wordMap.keySet();
		for(String first:Wordset)
		{
			
			WordList.add(wordMap.get(first));
			//System.out.println(first+"========"+wordMap.get(first).getCount());
			
		}
	}
	public static List<Word> getWordList() {
		return WordList;
	}
	public static void setWordList(List<Word> wordList) {
		WordList = wordList;
	}
	
}

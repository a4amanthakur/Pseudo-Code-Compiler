import java.util.StringTokenizer;
import javax.swing.JOptionPane;

abstract class KeywordFuction
{
	abstract String startCompilation(String code,javax.swing.JFrame frame);
	abstract void generateLines(String code);
	abstract StringBuilder verifyBeginEnd(String lines[]);
	abstract StringBuilder workOnLines(String codeLines[]);
	abstract StringBuilder display(String keywords[],int i,int line);
	abstract void declareNumeric(String keywords[],int line);
}

class InbuiltKeywords extends KeywordFuction
{
	private StringBuilder keywordOutput ,output,validSyntax,strValue[];
	private int i,isValid=0,index=0,numTop,j,charTop,strTop;
	private StringTokenizer strTokens;
	private String codeLines[],keywords[];
	private int numValue[];
	private String numName[],charName[],strName[];
	private char charValue[],varType;
	private javax.swing.JFrame frame;
	InbuiltKeywords()
	{
		keywordOutput=new StringBuilder("");
		output=new StringBuilder("");
		validSyntax=new StringBuilder("");
		numTop=0;
		numName=new String[50];
		numValue=new int[50];
		charName=new String[50];
		charValue=new char[50];
		strName=new String[50];
		strValue=new StringBuilder[50];
	}

	
	// function to check the right/wrong keywords which are used in code 
	@Override
	String startCompilation(String code,javax.swing.JFrame frame)
	{
		this.frame=frame;
		//initializing codeLines[] with code lines
		generateLines(code);
		output.delete(0,output.length());
		//verify SYntax::::::
		output.append(verifyBeginEnd(codeLines));

		if(isValid==1)
		{
			keywordOutput.delete(0,keywordOutput.length());
			//divide the lines int keywords or tokens;
			numTop=0;
			charTop=0;
			strTop=0;
			for(i=0;i<numName.length;i++)
			{
				numName[i]="";
				numValue[i]=0;
			}
			for(i=0;i<charName.length;i++)
			{
				charName[i]="";
				charValue[i]=' ';
			}
			for(i=0;i<strName.length;i++)
			{
				strName[i]="";
				strValue[i]=new StringBuilder("");
			}
			keywordOutput.append(workOnLines(codeLines));
		}
		else if(isValid==-1)
		{
			keywordOutput.delete(0,keywordOutput.length());
			keywordOutput.append(output);
		}
		return keywordOutput.toString();
	}

	//seperating the lines from whole code
	@Override
	void generateLines(String code)
	{
		codeLines=new String[code.length()];
		codeLines=code.split("[\n]");
	}

	//*************JUST FOR BEGIN AND END KEYWORD**************//
	@Override
	StringBuilder verifyBeginEnd(String lines[])
	{

		//***************TEST BEGIN/END STRUCTURE****************//
		validSyntax.delete(0,validSyntax.length());
		i=0;

		
		while(i<1)
		{
				strTokens=new StringTokenizer(lines[0]," \t",true);
				keywords=new String[strTokens.countTokens()];
				index=0;
				j=0;
				while(strTokens.hasMoreTokens())
				{
					keywords[index]=strTokens.nextToken();
					if(keywords[index].equals(" ") && j==0)
					{
						//do nothing
					}
					else if(j==0 && keywords[index].equals("\t"))
					{
						//do nothing
					}
					else
					{
						index++;
						j++;	
					}
				}
				if(keywords[0].equals("begin"))
				{
					isValid=1;
				}
				else
				{
					isValid=-1;
					validSyntax.append("\nInvalid begin/end Syntax.");
					break;
				}

				index=0;
				j=0;
				strTokens=new StringTokenizer(lines[(lines.length)-1]," \t",true);
				keywords=new String[strTokens.countTokens()];
				while(strTokens.hasMoreTokens())
				{
					keywords[index]=strTokens.nextToken();
					if(keywords[index].equals(" ") && j==0)
					{
						//do nothing
					}
					else if(j==0 && keywords[index].equals("\t"))
					{
						//do nothing
					}
					else
					{
						index++;
						j++;	
					}
				}

				if(keywords[0].equals("end"))
				{
					isValid=1;
				}
				else
				{
					isValid=-1;
					validSyntax.append("\nInvalid begin/end Syntax.");
					break;
				}	
				i++;
		}//end of while	
		//*********************END OF BEGIN/END TEST***************//


		return validSyntax;
	}


	@Override
	StringBuilder workOnLines(String codeLines[])
	{

		for (i=1;i<codeLines.length-1;i++ )
		{
			
				index=0;
				j=0;
				strTokens=new StringTokenizer(codeLines[i]," ,;=><()!\'\"\t",true);
				keywords=new String[strTokens.countTokens()];
				while(strTokens.hasMoreTokens())
				{
					keywords[index]=strTokens.nextToken();
					System.out.println(index+":"+keywords[index]);
					if(keywords[index].equals(" ") && j==0)
					{
						//do nothing
					}
					else if(j==0 && keywords[index].equals("\t"))
					{
						//do nothing
					}
					else
					{
						index++;
						j++;	
					}

				}	
				index--;
				
				if(index<0)
				{

				}
				else if(keywords[index].equals(";"))
				{

					switch(keywords[0])
					{
						case "display":
						{
							index--;
							output.append(display(keywords,index,i));
							output.append("\n");
							break;
						}
						case "accept":
						{
							index--;
							accept(keywords,index,i);
							break;
						}
						case "numeric":
						{
							declareNumeric(keywords,i);
							break;
						}
						case "char":
						{
							declareChar(keywords,i);
							break;
						}
						case "string":
						{
							index--;
							declareString(keywords,i);
							break;
						}
						case "if":
						{
							ifStatement(keywords,index,i);
							break;
						}

						default:
						{
							
							System.out.println("this is default case:");
							break;
						}

					}//end of switch case
				
				}
				else
				{
					output.delete(0,output.length());
					output.append("\nMissing ; at line:"+(i+1));	
					break;
				}
		}
		return output;
	}

	void accept(String keywords[],int i,int line)
	{
		if(keywords[i].equals(")") && keywords[1].equals("("))
		{
					i--;
					index=2;
					while(!keywords[index].equals(")"))
					{
						if(keywords[index].equals(","))
						{
							index++;
						}
						else if(keywords[index].equals(" "))
						{
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='n')
						{
							try
							{
								numValue[validVariable(keywords[index])]=Integer.parseInt(readValue(keywords[index],varType));
								index++;
							}
							catch(Exception e)
							{
								output.delete(0,output.length());
								output.append("\n Error: value must be numeric type \n");
								break;
							}
							
						}
						else if(validVariable(keywords[index])>=0 && varType=='c')
						{
							charValue[validVariable(keywords[index])]=readValue(keywords[index],varType).charAt(0);
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='s')
						{
							strValue[validVariable(keywords[index])].append(readValue(keywords[index],varType));
							index++;
						}
						else
						{
							output.delete(0,output.length());
							output.append("\n Error at line : "+line+"\nNot a valid variable \n");
							break;
						}
					}//end of while				
		}
		else
		{
			output.delete(0,output.length());
			output.append("\nMissing '(' or ')' at line:"+(line+1));
		}//end of else if(())
	}
	String readValue(String var,char type)
	{
		String data;
		if(type=='n')
		{
			data= JOptionPane.showInputDialog("Enter numeric value for: "+var);	
		}
		else if(type=='c')
		{
			data=JOptionPane.showInputDialog("Enter char value for: "+var);	
		}
		else if(type=='s')
		{
			data=JOptionPane.showInputDialog("Enter string value for: "+var);	
		}
		else
		{
			data="";
		}
		return data;
		
	}
	void ifStatement(String keywords[],int l,int line)
	{
		l--;
		if(keywords[l].equals(")") && keywords[1].equals("("))
		{
					index=2;
					while(!keywords[index].equals(")"))
					{
						if(keywords[index].equals(">"))
						{
							index++;
						}
						else if(keywords[index].equals("<"))
						{
							index++;
						}
						else if(keywords[index].equals("="))
						{
							index++;
						}
						else if(keywords[index].equals("!"))
						{
							index++;
						}
						else if(keywords[index].equals("&&"))
						{
							index++;
						}
						else if(keywords[index].equals("||"))
						{
							index++;
						}
						else if(keywords[index].equals(" ") || keywords[index].equals("	"))
						{
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='n')
						{
							try
							{
								index++;
							}
							catch(Exception e)
							{
								output.delete(0,output.length());
								output.append("\n Error: value must be numeric type \n");
								break;
							}
							
						}
						else if(validVariable(keywords[index])>=0 && varType=='c')
						{
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='s')
						{
							index++;
						}
						else
						{
							System.out.println("Error:"+keywords[index]);
							output.delete(0,output.length());
							output.append("\n Error at line "+line+": Not a valid variable. \n");
							break;
						}
					}//end of while				
		}
		else
		{
			output.delete(0,output.length());
			output.append("\nMissing '(' or ')' at line:"+(line+1));
		}//end of else if(())	
	}
	

	void declareString(String keywords[],int line)
	{
		//System.out.println("hii numeric");
		line++;
		index=1;		

		while(!keywords[index].equals(";"))
		{
			if(keywords[index].equals(" "))
			{
				index++;
			}
			else if(keywords[index].equals(","))
			{
				index++;
			}
			else if(keywords[index].equals("="))
			{
				index++;
				try
				{
					if(keywords[index].equals("\""))
					{
						index++;
						while(!keywords[index].equals("\"") || !keywords[index].equals(";"))
						{
							if(keywords[index].equals("\""))
							{
								index++;
								break;
							}

							else if(keywords[index].equals(" "))
							{
								strValue[strTop-1].append(keywords[index]);
								index++;
							}
							else if(keywords[index].equals(";"))
							{
								strValue[strTop-1].delete(0,strValue.length);
								output.delete(0,output.length());
								output.append("\n Invalid Syntax: Missing \" at line:"+line);
								break;
							}
							else
							{
								strValue[strTop-1].append(keywords[index]);
								index++;
							}
						}
					}
					else
					{
						output.delete(0,output.length());
						output.append("\n Invalid Syntax: Missing \" at line:"+line);
						break;
					}
				}
				catch(NumberFormatException e)
				{	
					output.delete(0,output.length());
					output.append("\n Invalid data input at line:"+line);
					break;
				}
			}
			else
			{	
					if(validVariable(keywords[index])==-1 && validVarName(keywords[index])==true)
					{
						strName[strTop]=keywords[index];
						strValue[strTop].append("");
						strTop++;
						index++;
					}
					else
					{
						keywordOutput.delete(0,keywordOutput.length());
						keywordOutput.append("\n Error at line : "+line+"\nWrong variable declaration syntax or "+keywords[index]+" is already defined.\n");
						break;
					}	
			}

		}

	}




	
	void declareChar(String keywords[],int line)
	{
		//System.out.println("hii numeric");
		line++;
		index=1;		

		while(!keywords[index].equals(";"))
		{
			if(keywords[index].equals(" "))
			{
				index++;
			}
			else if(keywords[index].equals(","))
			{
				index++;
			}
			else if(keywords[index].equals("="))
			{

				try
				{
					if(keywords[index+1].equals("\'"))
					{
						if(keywords[index+3].equals("\'"))
						{
							charValue[charTop-1]=keywords[index+2].charAt(0);
							index=index+4;
						}
						else
						{
							output.delete(0,output.length());
							output.append("\n Invalid Syntax: Missing \' at line:"+line);
							break;
						}
					}
					else
					{
						output.delete(0,output.length());
						output.append("\n Invalid Syntax: Missing \' at line:"+line);
						break;
					}
				}
				catch(NumberFormatException e)
				{	
					output.delete(0,output.length());
					output.append("\n Invalid data input at line:"+line);
					break;
				}
			}
			else
			{	
					if(validVariable(keywords[index])==-1 && validVarName(keywords[index])==true)
					{
						charName[charTop]=keywords[index];
						charValue[charTop]=0;
						charTop++;
						index++;
					}
					else
					{
						keywordOutput.delete(0,keywordOutput.length());
						keywordOutput.append("\n Error at line : "+line+"\nWrong variable declaration syntax or "+keywords[index]+" is already defined.\n");
						break;
					}	
			}

		}

	}

	
	void declareNumeric(String keywords[],int line)
	{
		//System.out.println("hii numeric");
		line++;
		index=1;		

		while(!keywords[index].equals(";"))
		{
			if(keywords[index].equals(" "))
			{
				//System.out.println("space:");
				index++;
			}
			else if(keywords[index].equals(","))
			{
				//System.out.println(",");
				index++;
			}
			else if(keywords[index].equals("="))
			{
				try
				{
					numValue[numTop-1]=Integer.parseInt(keywords[index+1]);
					index=index+2;
				}
				catch(NumberFormatException e)
				{	
					output.delete(0,output.length());
					output.append("\n Invalid data input at line:"+line);
					break;
				}
			}
			else
			{	
				

					if(validVariable(keywords[index])==-1 && validVarName(keywords[index])==true)
					{
						numName[numTop]=keywords[index];
						numValue[numTop]=0;
						numTop++;
						index++;
					}
					else
					{
						keywordOutput.delete(0,keywordOutput.length());
						keywordOutput.append("\n Error at line : "+line+"\nWrong variable declaration syntax or "+keywords[index]+" is already defined.\n");
						break;
					}
				

			}

		}
	}//end of method


	boolean validVarName(String var)
	{
		boolean res=true;
			if(var.charAt(0)>='a'  &&  var.charAt(0)<='z'|| var.charAt(0)>='A'  &&  var.charAt(0)<='Z')
			{
				res=true;
			}
			else
			{
				 res=false;
			}
		return res;
	}
	int validVariable(String var)
	{
		int res=-1;
				for(int c=0;c<numName.length;c++)
				{

					if(numName[c].equals(var)) 
					{
						res=c;
						varType='n';
						break;
					}
					else
					{

						res=-1;
					}
				}
			if(res==-1)
			{
				for(int c=0;c<charName.length;c++)
				{

					if(charName[c].equals(var)) 
					{
						res=c;
						varType='c';
						break;
					}
					else
					{

						res=-1;
					}
				}
			}
			if(res==-1)
			{
				for(int c=0;c<strName.length;c++)
				{

					if(strName[c].equals(var)) 
					{
						res=c;
						varType='s';
						break;
					}
					else
					{

						res=-1;
					}
				}
			}
		return res;
	}
	
	@Override
	StringBuilder display(String keywords[],int i,int line)
	{
		StringBuilder myData=new StringBuilder("");
		if(keywords[i].equals(")") && keywords[1].equals("("))
		{
			i--;
			if(keywords[i].equals("\"") && keywords[2].equals("\""))
			{
				index=3;
				while(index<i)
				{
					myData.append(keywords[index]);
					index++;
				}
			}
			else 
			{
				if(validVariable(keywords[2])>=0)
				{
					index=2;
					while(!keywords[index].equals(")"))
					{
						if(keywords[index].equals(","))
						{
							index++;
						}
						else if(keywords[index].equals(" "))
						{
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='n')
						{
							myData.append(numValue[validVariable(keywords[index])]+"\n");
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='c')
						{
							myData.append(charValue[validVariable(keywords[index])]+"\n");
							index++;
						}
						else if(validVariable(keywords[index])>=0 && varType=='s')
						{
							myData.append(strValue[validVariable(keywords[index])]+"\n");
							index++;
						}
						else
						{
							myData.delete(0,myData.length());
							myData.append("\nError at line:"+(line+1)+"\nInvalid Statement\n");
							break;
						}
					}
					
				}
				else 
				{
					myData.delete(0,myData.length());
					myData.append("\nMissing \" or \" at line:"+(line+1));
				}
			}
		}
		else
		{
			myData.delete(0,myData.length());
			myData.append("\nMissing '(' or ')' at line:"+(line+1));
		}
		//keywords.append("\n"+str);
		return myData;
	}

	

	
	public static void main(String[] args) {
		
	}
}//end of class
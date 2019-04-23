class Sample{

public static void main(String[] args) 
{
System.out.println("dd");
try{
hey();
}
catch(Exception e){
System.out.println("error");
}

}

public static void hey() throws Exception{
System.out.println("dd");

Boolean abc= true;
if(abc)
throw new Exception();

System.out.println("dd2");
System.out.println("dd3");System.out.println("dd4");
System.out.println("dd5");

}
}

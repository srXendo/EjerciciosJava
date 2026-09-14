class for_regresivo{
	static public void main(String[] argsv){
		int contador;
		for(contador = 10; contador >= 1; contador--){
			System.out.println(contador);
		}
		System.out.println("Despegue!.");
		System.out.println("Reiniciamos contador");
		for(; contador <=10; contador++){
			System.out.println("volvemos: "+contador);
		}
		System.out.println("fin");
	}
}
package cn.appleye.randomcontact.common.factory;

public class OrganizationFactory implements IFactory{
	public static String[] sSample = {
			"Baidu","Google","Alibaba","Tencent","Yahoo","Apple","VIVO","OPPO","Xiaomi","ICBC","China Construction Bank",
			"Agricultural Bank of China","Bank of China","Berkshire Hathaway","JPMorgan Chase","Exxon Mobil","PetroChina",
			"General Electric","Wells Fargo","Toyota Motor","Apple","Royal Dutch Shell","Volkswagen Group","HSBC Holdings",
			"Chevron","Wal-Mart Stores","Samsung Electronics","Citigroup","China Mobile","Allianz","Verizon Communications",
			"Bank of America","Sinopec","Microsoft","Daimler","AT&T","Gazprom","AXA Group","Nestle","Banco Santander",
			"Ping An Insurance Group","Mitsubishi UFJ Financial","Johnson & Johnson","Total","Procter & Gamble","China Life Insurance",
			"Bank of Communications","Google","Vodafone","BP","American International Group","Itaú Unibanco Holding","IBM","BMW Group",
			"Comcast","Commonwealth Bank","Pfizer","Goldman Sachs Group","BHP Billiton","MetLife","Novartis","Royal Bank of Canada",
			"Siemens","China Merchants Bank","Prudential","Anheuser-Busch InBev","Nippon Telegraph & Tel","Rosneft","Westpac Banking Group",
			"Banco Bradesco","Softbank","Honda Motor","General Motors","UnitedHealth Group","TD Bank Group","Intel","EDF","Ford Motor",
			"Deutsche Telekom","BASF","Boeing","Industrial Bank","UBS","ANZ","Cisco Systems","Sumitomo Mitsui Financial","Zurich Insurance Group",
			"China Minsheng Banking","Merck & Co","Roche Holding","Citic Pacific","National Australia Bank","Shanghai Pudong Development",
			"Walt Disney","CVS Caremark","Telefónica","Oracle","ConocoPhillips","Sanofi","United Technologies","ING Group","Coca-Cola",
			"China Citic Bank","Morgan Stanley","Hewlett-Packard","Nissan Motor","GDF SUEZ","PepsiCo","Lloyds Banking Group","Munich Re",
			"Société Générale","CNOOC","Statoil","American Express","Rio Tinto","Bank of Nova Scotia","Bayer","LukOil","Glencore International",
			"Credit Agricole","China State Construction Engineering","Unilever","Deutsche Bank","BBVA-Banco Bilbao Vizcaya","Saudi Basic Industries",
			"Huawei","Hyundai Motor","Hutchison Whampoa","Mizuho Financial","EADS","Eni","Hon Hai Precision","Schlumberger",
			"Sberbank","América Móvil","US Bancorp","China Shenhua Energy","Manulife Financial","Generali Group","SAIC Motor",
			"Mitsubishi Corp","Nordea Bank","Banco do Brasil","Home Depot","GlaxoSmithKline","AIA Group","Caterpillar","UniCredit Group",
			"Credit Suisse Group","Dow Chemical","Capital One Financial","Intesa Sanpaolo","Reliance Industries","China Telecom","China Everbright Bank",
			"Swiss Re","Phillips Hitachi-M?ller-Maersk","Twenty-First Century Fox","Inc.","Iberdrola","State Bank of India","Standard Chartered",
			"Bank of Montreal","China Communications Construction","Qualcomm","Prudential Financial","Taiwan Semiconductor","Honeywell International",
			"China Railway Group","Amgen","Aviva","ESExpress Scripts","Time Warner","VINCI","Walgreens Boots Alliance","Anthem","Union Pacific",
			"China Railway Construction","PNCPNC Financial Services","Korea Electric Power","McKesson","China Pacific Insurance",
			"EI du Pont de Nemours","Legal & General Group","KDDIKDDI","National Grid","L'Oréal Group","Travelers Cos","Christian Dior",
			"Tokio Marine Holdings","Valero Energy","Oil & Natural Gas","Mitsui & Co","China Unicom","·Philip Morris International",
			"United Parcel Service","Mondelēz International","Suncor Energy","SAPSAP","Duke Energy","Gilead Sciences","Renault","Allstate",
			"Deutsche Post","Lockheed Martin","OrangeOrange","Ecopetrol","","British American Tobacco","Costco Wholesale","ABBABB",
			"Bank of New York Mellon","Aetna","Brookfield Asset Management","Schneider Electric","McDonald's","People's Insurance Company",
			"Surgutneftegas","Lowe's Cos","Jardine Matheson","Aflac","Continental","BT Group","Japan Tobacco","ACEACE","SABMiller","Repsol YPF",
			"John Deere","Archer Daniels Midland","American Airlines Group","EMC","BlackRock","FedEx","PTT PCL","NextEra Energy","Cigna",
			"Teva Pharmaceutical Inds","Enel","Aegon","Canadian Imperial Bank","Denso","Natixis","Dalian Wanda Commercial Properties","Canon",
			"Carrefour","Altria Group","ISeven & I Holdings","AstraZeneca","Dai-ichi Life Insurance","Itochu","RWE Group","Wesfarmers",
			"Telstra","Diageo","Enbridge","Nippon Steel & Sumitomo Metal","Abbott Laboratories","Medtronic","Southern Co","China Vanke",
			"East Japan Railway","General Dynamics","Halliburton","Exelon","Time Warner Cable","Canadian Natural Resources","DNB","Imperial Tobacco Group",
			"KBC Group","Kroger","LyondellBasell Industries","Tata Motors","Visa","Eli Lilly & Co","Nomura Holdings","Danaher","DBS Group",
			"Alibaba","Bridgestone","MS&ADMS&AD Insurance","Telecom Italia","Huaxia Bank","Marathon Petroleum","Gas Natural Fenosa","Ericsson",
			"Sun Life Financial","Saint-Gobain","Shinhan Financial Group","FacebookFacebook","SEB","Vivendi","Fresenius","ICICI Bank","Danone",
			"CNP Assurances","Kinder Morgan","Directv","Fiat Chrysler Automobiles","NIKE","Central Japan Railway","EOGEOG Resources",
			"Thermo Fisher Scientific","Linde","BCE","Sun Hung Kai Properties","Air Liquide","Hyundai Mobis","Bristol-Myers Squibb","Samsung Life Insurance",
			"HCAHCA Holdings","Oversea-Chinese Banking","AbbVie","Tencent Holdings","Mitsubishi Electric","State Street"
			,"Reckitt Benckiser Group","New China Life Insurance","Bank of Beijing","KIA Motors","American Electric","Johnson Controls",
			"Svenska Handelsbanken","Henkel","WPPWPP","Raytheon","Inditex","Eaton","Fubon Financial","Power Corp of Canada","Swedbank",
			"Accenture","BB&TBB&T","Cathay Financial","SingTel","PG&E","Cardinal Health","Old Mutual","Standard Bank Group",
			"CaixaBankCaixaBank","Devon Energy","Poly Real Estate","Emerson Electric","Mitsubishi Heavy Industries","Ameriprise Financial",
			"Northrop Grumman","Investor AB","Delta Air Lines","Woolworths","Dominion Resources","National Oilwell Varco","Baker Hughes","Chubb",
			"Merck","United Continental Holdings","Baxter International","Nokia","United Overseas Bank","Indian Oil","Holcim","Carnival",
			"Fannie Mae","Huaneng Power International","SKSK Hynix","Micron Technology","Takeda Pharmaceutical","Toshiba","Discover Financial Services",
			"Standard Life","Monsanto","Lincoln National","Teliasonera","Las Vegas Sands","Occidental Petroleum","Canadian National Railway",
			"Sasol","KB KB Financial Group","BNP Paribas","Wilmar International","Automatic Data","Maybank","Hartford Financial Services","Danske Bank"
			,"Richemont","Humana","HDFCHDFC Bank","Freddie Mac","BAE Systems","Femsa","Heineken Holding","Qatar National Bank","Edison International",
			"Etisalat","CSXCSX","Gree Electric Appliances","Telenor","Marubeni","Posco","EXOREXOR","Macquarie Group","EDP-Energias de Portugal","CBS",
			"Hess","Thomson Reuters","MTN Group","SunTrust Banks","FirstRand","Norfolk Southern","Husky Energy","PPLPPL","Bouygues","Baoshan Iron & Steel",
			"Mapfre","Barclays-Saudi Telecom","TransCanada","General Mills","Tenaga Nasional","Williams Cos","Kraft Foods","JFE Holdings","Macy's",
			"Vale","Novo Nordisk","Michelin Group","Petrobras-Magna International","Texas Instruments","International Airlines","Toyota Industries",
			"Sumitomo Mitsui Trust","Marathon Oil","Fuji Heavy Industries","Illinois Tool Works","Viacom","British Sky Broadcasting","Allied Irish Banks",
			"Sampo","Charles Schwab","Royal Bank of Scotland-NTPC","Citic Securities","Mitsui Fudosan","China Resources Land","ACSGrupo ACS",
			"Midea Group Co. Ltd.","Principal Financial Group","CenturyLinkCenturyLink","Fujifilm Holdings","Pernod Ricard","Corning","VoyaVoya Financial",
			"CSR","TalanxTalanx","Colgate-Palmolive-Kimberly-Clark","Lenovo Group","AMP","Garanti Bank","Syngenta","CNH Industrial",
			"Metallurgical Corp of China","JBSJBS","Komatsu","Sempra Energy","SKSK Telecom","Bank Rakyat Indonesia","Amazon.com","TJXTJX Cos",
			"Starbucks","Aon","Associated British Foods","PPGPPG Industries","Public Service Enterprise Group","Sinohydro Group","TE Connectivity",
			"Yahoo","E.ON-Mitsubishi Estate","Swisscom","Orix","Philips","Consolidated Edison","Saudi Electricity","","China CNR","Fujitsu","Sony",
			"MasterCard","Suzuki Motor","China Cinda Asset Management","Southwest Airlines","Chesapeake Energy","Praxair","HDFC","Tata Consultancy Services",
			"Paccar","National Commercial Bank","T&DT&D Holdings","Bank Mandiri","Coal India","Compass Group","Inpex","Progressive","CRH","Ko?Ko? Holding",
			"Sysco","Ecolab","Tyson Foods","Evergrande Real Estate"
	};
	
	public static String[] sSampleJob = {
			"Engineer", "CEO", "CTO", "Manager", "Minister", "Chairman", "Marketing minister", "Strategy minister", "COO", "CFO",
			"CIO", "HRD", "OD", "MD", "OM", "PM", "PG", "GM", "VP", "FVP", "AVP", "Vice-President of Sales", "Senior Customer Manager",
			"Sales Manager", "Regional Sales Manager", "Merchandising Manager", "Sales Assistant", "Wholesale Buyer", "Tele-Interviewer",
			"Real Estate Appraiser", "Marketing Consultant", "Marketing and Sales Director", "Market Research Analyst", "Manufacturer's Representative",
			"Director of Subsidiary Rights", "Regional Customer Manager", "Sales Administrator", "Telemarketing Director", "Systems Engineer",
			"Hardware Engineer", "Applications Programmer", "Information Analyst", "LAN Systems Analyst", "Statistician", "Director of Human Resources",
			"Assistant Personnel Officer", "Import/Export Manager", "Property Manager", "Inventory Control Manager", "Food Service Manager",
			"Production Manager", "Telecommunications Manager", "Assistant Store Manager", "Developmental Engineer", "Telemarketer", "Computer Operator  Product Support Manager",
			"Advertising Manager", "Sales Executive", "MIS Manager", "Project Manager", "Employer Relations Representative", "HMO Product Manager"
	};

	private int mMinCount;
	private int mMaxCount;

	public OrganizationFactory() {
		this(1, 1);
	}

	public OrganizationFactory(int minCount, int maxCount) {
		mMinCount = minCount;
		mMaxCount = maxCount;
	}
	
	public static String creatRandomOrganization() {
		int index = (int)(Math.random() * sSample.length);
		String Organization = sSample[index];
		
		return Organization;
	}
	
	public static String createRandomJob() {
		int index = (int)(Math.random() * sSampleJob.length);
		String job = sSampleJob[index];
		
		return job;
	}

	@Override
	public String createFirstRandomData() {
		return creatRandomOrganization();
	}

	public String[] createRandomOrg(int count, boolean allowRepeat) {
		String[] orgs = new String[count];
		
		for(int i=0; i<count; i++) {
			String number= creatRandomOrganization();
			
			orgs[i] = number;
			boolean willRepeat = false;
			if(allowRepeat && i<count-1) {
				willRepeat = (int)(Math.random()*2)==1;
			}

			if (willRepeat) {
				i++;
				orgs[i] = number;
			}
		}
		
		return orgs;
	}

	@Override
	public String[] createFirstRandomData(boolean repeatAllowed) {
		int count = mMinCount + (int)((mMaxCount - mMinCount + 1)*Math.random());
		return createRandomOrg(count, repeatAllowed);
	}

	@Override
	public String createSecondRandomData() {
		return createRandomJob();
	}
}

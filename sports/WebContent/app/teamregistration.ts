import { Component, ElementRef, Renderer } from '@angular/core';
import { Commonservice }  from './Commonservice';
@Component({
  selector: 'teamregistrations',
  templateUrl:'app/teamregistration.html',
   providers:[Commonservice]
})
export class TeamRegistration  {
	registrationID:any;
	favoriteSeason:any;
	a:any={}
	firstname:any='';
	lastname:any='';
	middlename:any='';
	addr1:any='';
	addr2:any='';
	nameOfSchoolOrClub:any='';
	addressOfSchoolOrClub:any='';
	address2OfSchoolOrClub:any='';
	dob:any;
	yrs:any='';
	months:any='';
	contactno:any='';
	alternativeno:any='';
	email:any='';
	gender:any='';
	minDate:any;
	maxDate:any;
	nexttext:any='';
	err:any=[];
	pincode:any='';
	city:any='';
	state:any='';
	schoolpincode:any='';
	schoolcity:any='';
	schoolstate:any='';
	record={};
	
	

years:any=[];
typeofFN(obj){
	return typeof obj;
}
constructor(public el: ElementRef, public renderer: Renderer,public services:Commonservice) {
    // el.nativeElement.style.backgroundColor = 'yellow';
	//console.log(el);
	this.gender="B";
	this.middlename='';
	this.yrs=0;
	this.months=0;
    renderer.setElementStyle(el.nativeElement, 'backgroundColor', 'yellow');
  }
	
  isEligible(e){
	  let minage=e[this.getKey(e)[1]];
	  return (minage>this.yrs|| (minage==this.yrs & this.months==0& this.dayofbirth==0))  &&  (minage <=21 || (minage==this.yrs & this.months==0 && this.yrs==21 & this.dayofbirth==0))
  }
	ngOnInit(){
		for(let i=1970;i<=new Date().getFullYear()-10;i++){
			this.years.push(i);
		}
		this.gender="B";
		this.nexttext="Next";
	}
	isLeapyear(){
		if(this.year!=undefined&&(((this.year % 4 == 0) && (this.year % 100 != 0)) || (this.year % 400 == 0)))
			return true;
		return false;
	}
	day:any;
	month:any;
	year:any;
	bankDetails:any="";
	pday:any;
	pmonth:any;
	pyear:any;
	valideDate=false;
	dayofbirth=0;
	calAge(e){
		let d=31;
		if(this.day!=undefined && this.month!=undefined && this.year!=undefined){
			if(this.month=='February'){
			 if((this.isLeapyear() && this.day<30)||this.day<29){
				 d=28;
				 if(this.isLeapyear()){
					d=28;	 
				 }
				 this.valideDate=true;
			 }
			}else if(this.month=='April' || this.month=='June' ||this.month=='September' || this.month=='November' ){
				if(this.day<31){
				 this.valideDate=true;
				 d=31;	 
			 }
			}else{
				this.valideDate=true;
			}
			
			if(this.valideDate){
				console.log(this.month+'/'+ this.day+'/'+this.year);
				this.dob=new Date(this.month+'/'+ this.day+'/'+this.year);
				console.log(this.dob);
				let ageDifMs = Date.now() - new Date(this.month+'/'+ this.day+'/'+this.year).getTime();
				let ageDate = new Date(ageDifMs);
				this.dayofbirth=d-ageDate.getDate();
				this.months=12-(ageDate.getMonth()+1);
				this.yrs=Math.abs(ageDate.getUTCFullYear() - 1970);
				this.valideDate=false;
				this.makeSportList();
			}
			
		}
		
	}
	previous(){
		this.nexttext="Next";
	}
	selectedValue:any;
	selectDecipline(){
		this.go();
	}
	arrylist:any=[];
	addlist:any=[];
	getKey(obj:any){
		return Object.keys(obj);
	}
	add(e:any){
		this.addlist.push({"eventid":this.getKey(e)[0],"Decipline":this.selectedValue,"text":e[this.getKey(e)[0]]});
		return false;
	}
	delet(e){
		let temp=this.addlist.filter((el)=>{return el.eventid!=e});
		this.addlist=temp;
		return false;
	}
	getKeys(obj){
		return Object.keys(obj)
	}
	arrlist1={}
	next(){
		console.log(this.gender);
		this.err=[];
		this.isEmpty(this.firstname,"Name");
		this.isEmpty(this.lastname,"Surname");
		this.isEmpty(this.addr1,"Address");
		this.isEmpty(this.nameOfSchoolOrClub,"Name Of the School/Club");
		this.isEmpty(this.addressOfSchoolOrClub,"School/Club Address");
		this.isEmpty(this.contactno,"Mobile Number");
		this.isEmpty(this.alternativeno,"Emergence Number");
		this.isEmpty(this.email,"Email-Id");
		this.isEmpty(this.state,'State');
		this.isEmpty(this.city,'City');
		this.isEmpty(this.pincode,'Pin Code');
		this.isEmpty(this.schoolstate,'State of school');
		this.isEmpty(this.schoolcity,'City of school');
		this.isEmpty(this.schoolpincode,'Code of school');
		if(this.nexttext=="Submit"){
			this.insertRecord();
		}
		this.arrlist1={};
		/*if(this.err.length==0){
			let temp={};
			for(let i=0;i<this.list.length;i++){
				if(this.arrlist[this.list[i]][this.gender]!=undefined){
					temp[this.list[i]]=this.arrlist[this.list[i]][this.gender].filter((e)=>{return this.isEligible(e);});
				}
			}
			
			this.arrlist1=temp;
			this.nexttext="Submit";
		}*/
		
		return false;
	}
	makeSportList(){
		this.arrlist1={};
		let temp={};
		for(let i=0;i<this.services.list.length;i++){
			if(this.services.arrlist[this.services.list[i]][this.gender]!=undefined){
				temp[this.services.list[i]]=this.services.arrlist[this.services.list[i]][this.gender].filter((e)=>{return this.isEligible(e);});
			}
		}
		this.arrlist1=temp;
	}
	addorremove(event:any,obj:any){
		if(event.target.querySelector('[type="checkbox"]').checked==false){
			this.add(obj);
		}else{
			this.delet(this.getKey(obj)[0]);
		}
	}
	insertRecord() {
		this.address2OfSchoolOrClub="";
		this.addr2="";
		
		let participantDetails={
			"firstname":this.firstname,
			"lastname":this.lastname,
			"middlename":this.middlename,
			"addr1":this.addr1,
			"addr2":this.addr2,
			"state":this.state,
			"city":this.city,
			"pincode":this.pincode,
			"nameOfSchoolOrClub":this.nameOfSchoolOrClub,
			"addressOfSchoolOrClub":this.addressOfSchoolOrClub,
			"address2OfSchoolOrClub":this.address2OfSchoolOrClub,
			"schoolstate":this.schoolstate,
			"schoolcity":this.schoolcity,
			"schoolpincode":this.schoolpincode,
			"dob":this.dob,
			"age":this.yrs+','+this.months,
			"contactno":this.contactno,
			"alternativeno":this.alternativeno,
			"email":this.email,
			"gender":this.gender,
			"bankDetails":this.bankDetails,
			"paymentdate":new Date(this.pmonth+'/'+ this.pday+'/'+this.pyear)
		};
		this.record={
			partidetails:participantDetails,
			games:this.addlist
		}
		let xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		} else {  // code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		let that=this;
		xmlhttp.open("POST", "poll_vote.php");
		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlhttp.send("record="+JSON.stringify(this.record));
		xmlhttp.onreadystatechange = ()=>{
			if (xmlhttp.readyState == 4) {
				console.log(xmlhttp);
				this.addedSuccesfully=true;
				this.registrationID=xmlhttp.responseText;
			}
		};
	}
	addedSuccesfully=false;
	
	isEmpty(val:any,errmsg:any){
		(val.trim()=='')?this.err.push(errmsg+" is Required"):'';
	}

}

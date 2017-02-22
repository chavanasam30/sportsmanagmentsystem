import { Component, ElementRef, Renderer } from '@angular/core';
import {MdDialog, MdDialogRef} from '@angular/material';
import { TeamRegistration }  from './teamregistration';
import { Commonservice }  from './Commonservice';
@Component({
  selector: 'team-maker',
  templateUrl:'app/teammaker.html',
  providers:[Commonservice]
})
export class TeamMaker  {
	dia:any=TeamRegistration;
	record={};
	categorylist=['B','G'];
	agegroup=[];
	memeberlist=[];
	addMember(e){
	
	}
openDialog(e) {
    let dialogRef = this.dialog.open(TeamRegistration);
    dialogRef.afterClosed().subscribe(result => {
     // this.selectedOption = result;
    });
  }
constructor(public el: ElementRef, public renderer: Renderer,public dialog: MdDialog,public services:Commonservice) {
  }
  populateages(e){
		this.agegroup=[];
		if(this.selectedcategory!=undefined && this.selecteddecipline!=undefined){
			this.agegroup=this.services.arrlist[this.selecteddecipline][this.selectedcategory];
		}
	}
 	editfn(e,member){
		
	}
	deletefn(e,member){
		
	}
	captianList=[];
	getCaptianList(e){
		this.captianList=[];
	}
}

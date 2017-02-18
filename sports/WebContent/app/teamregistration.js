"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var Commonservice_1 = require('./Commonservice');
var TeamRegistration = (function () {
    function TeamRegistration(el, renderer, services) {
        this.el = el;
        this.renderer = renderer;
        this.services = services;
        this.a = {};
        this.firstname = '';
        this.lastname = '';
        this.middlename = '';
        this.addr1 = '';
        this.addr2 = '';
        this.nameOfSchoolOrClub = '';
        this.addressOfSchoolOrClub = '';
        this.address2OfSchoolOrClub = '';
        this.yrs = '';
        this.months = '';
        this.contactno = '';
        this.alternativeno = '';
        this.email = '';
        this.gender = '';
        this.nexttext = '';
        this.err = [];
        this.pincode = '';
        this.city = '';
        this.state = '';
        this.schoolpincode = '';
        this.schoolcity = '';
        this.schoolstate = '';
        this.record = {};
        this.years = [];
        this.bankDetails = "";
        this.valideDate = false;
        this.dayofbirth = 0;
        this.arrylist = [];
        this.addlist = [];
        this.arrlist1 = {};
        this.addedSuccesfully = false;
        // el.nativeElement.style.backgroundColor = 'yellow';
        //console.log(el);
        this.gender = "B";
        this.middlename = '';
        this.yrs = 0;
        this.months = 0;
        renderer.setElementStyle(el.nativeElement, 'backgroundColor', 'yellow');
    }
    TeamRegistration.prototype.typeofFN = function (obj) {
        return typeof obj;
    };
    TeamRegistration.prototype.isEligible = function (e) {
        var minage = e[this.getKey(e)[1]];
        return (minage > this.yrs || (minage == this.yrs & this.months == 0 & this.dayofbirth == 0)) && (minage <= 21 || (minage == this.yrs & this.months == 0 && this.yrs == 21 & this.dayofbirth == 0));
    };
    TeamRegistration.prototype.ngOnInit = function () {
        for (var i = 1970; i <= new Date().getFullYear() - 10; i++) {
            this.years.push(i);
        }
        this.gender = "B";
        this.nexttext = "Next";
    };
    TeamRegistration.prototype.isLeapyear = function () {
        if (this.year != undefined && (((this.year % 4 == 0) && (this.year % 100 != 0)) || (this.year % 400 == 0)))
            return true;
        return false;
    };
    TeamRegistration.prototype.calAge = function (e) {
        var d = 31;
        if (this.day != undefined && this.month != undefined && this.year != undefined) {
            if (this.month == 'February') {
                if ((this.isLeapyear() && this.day < 30) || this.day < 29) {
                    d = 28;
                    if (this.isLeapyear()) {
                        d = 28;
                    }
                    this.valideDate = true;
                }
            }
            else if (this.month == 'April' || this.month == 'June' || this.month == 'September' || this.month == 'November') {
                if (this.day < 31) {
                    this.valideDate = true;
                    d = 31;
                }
            }
            else {
                this.valideDate = true;
            }
            if (this.valideDate) {
                console.log(this.month + '/' + this.day + '/' + this.year);
                this.dob = new Date(this.month + '/' + this.day + '/' + this.year);
                console.log(this.dob);
                var ageDifMs = Date.now() - new Date(this.month + '/' + this.day + '/' + this.year).getTime();
                var ageDate = new Date(ageDifMs);
                this.dayofbirth = d - ageDate.getDate();
                this.months = 12 - (ageDate.getMonth() + 1);
                this.yrs = Math.abs(ageDate.getUTCFullYear() - 1970);
                this.valideDate = false;
                this.makeSportList();
            }
        }
    };
    TeamRegistration.prototype.previous = function () {
        this.nexttext = "Next";
    };
    TeamRegistration.prototype.selectDecipline = function () {
        this.go();
    };
    TeamRegistration.prototype.getKey = function (obj) {
        return Object.keys(obj);
    };
    TeamRegistration.prototype.add = function (e) {
        this.addlist.push({ "eventid": this.getKey(e)[0], "Decipline": this.selectedValue, "text": e[this.getKey(e)[0]] });
        return false;
    };
    TeamRegistration.prototype.delet = function (e) {
        var temp = this.addlist.filter(function (el) { return el.eventid != e; });
        this.addlist = temp;
        return false;
    };
    TeamRegistration.prototype.getKeys = function (obj) {
        return Object.keys(obj);
    };
    TeamRegistration.prototype.next = function () {
        console.log(this.gender);
        this.err = [];
        this.isEmpty(this.firstname, "Name");
        this.isEmpty(this.lastname, "Surname");
        this.isEmpty(this.addr1, "Address");
        this.isEmpty(this.nameOfSchoolOrClub, "Name Of the School/Club");
        this.isEmpty(this.addressOfSchoolOrClub, "School/Club Address");
        this.isEmpty(this.contactno, "Mobile Number");
        this.isEmpty(this.alternativeno, "Emergence Number");
        this.isEmpty(this.email, "Email-Id");
        this.isEmpty(this.state, 'State');
        this.isEmpty(this.city, 'City');
        this.isEmpty(this.pincode, 'Pin Code');
        this.isEmpty(this.schoolstate, 'State of school');
        this.isEmpty(this.schoolcity, 'City of school');
        this.isEmpty(this.schoolpincode, 'Code of school');
        if (this.nexttext == "Submit") {
            this.insertRecord();
        }
        this.arrlist1 = {};
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
    };
    TeamRegistration.prototype.makeSportList = function () {
        var _this = this;
        this.arrlist1 = {};
        var temp = {};
        for (var i = 0; i < this.services.list.length; i++) {
            if (this.services.arrlist[this.services.list[i]][this.gender] != undefined) {
                temp[this.services.list[i]] = this.services.arrlist[this.services.list[i]][this.gender].filter(function (e) { return _this.isEligible(e); });
            }
        }
        this.arrlist1 = temp;
    };
    TeamRegistration.prototype.addorremove = function (event, obj) {
        if (event.target.querySelector('[type="checkbox"]').checked == false) {
            this.add(obj);
        }
        else {
            this.delet(this.getKey(obj)[0]);
        }
    };
    TeamRegistration.prototype.insertRecord = function () {
        var _this = this;
        this.address2OfSchoolOrClub = "";
        this.addr2 = "";
        var participantDetails = {
            "firstname": this.firstname,
            "lastname": this.lastname,
            "middlename": this.middlename,
            "addr1": this.addr1,
            "addr2": this.addr2,
            "state": this.state,
            "city": this.city,
            "pincode": this.pincode,
            "nameOfSchoolOrClub": this.nameOfSchoolOrClub,
            "addressOfSchoolOrClub": this.addressOfSchoolOrClub,
            "address2OfSchoolOrClub": this.address2OfSchoolOrClub,
            "schoolstate": this.schoolstate,
            "schoolcity": this.schoolcity,
            "schoolpincode": this.schoolpincode,
            "dob": this.dob,
            "age": this.yrs + ',' + this.months,
            "contactno": this.contactno,
            "alternativeno": this.alternativeno,
            "email": this.email,
            "gender": this.gender,
            "bankDetails": this.bankDetails,
            "paymentdate": new Date(this.pmonth + '/' + this.pday + '/' + this.pyear)
        };
        this.record = {
            partidetails: participantDetails,
            games: this.addlist
        };
        var xmlhttp;
        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        var that = this;
        xmlhttp.open("POST", "poll_vote.php");
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlhttp.send("record=" + JSON.stringify(this.record));
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4) {
                console.log(xmlhttp);
                _this.addedSuccesfully = true;
                _this.registrationID = xmlhttp.responseText;
            }
        };
    };
    TeamRegistration.prototype.isEmpty = function (val, errmsg) {
        (val.trim() == '') ? this.err.push(errmsg + " is Required") : '';
    };
    TeamRegistration = __decorate([
        core_1.Component({
            selector: 'teamregistrations',
            templateUrl: 'app/teamregistration.html',
            providers: [Commonservice_1.Commonservice]
        }), 
        __metadata('design:paramtypes', [core_1.ElementRef, core_1.Renderer, Commonservice_1.Commonservice])
    ], TeamRegistration);
    return TeamRegistration;
}());
exports.TeamRegistration = TeamRegistration;
//# sourceMappingURL=teamregistration.js.map
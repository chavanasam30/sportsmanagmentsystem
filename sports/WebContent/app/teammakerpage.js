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
var material_1 = require('@angular/material');
var teamregistration_1 = require('./teamregistration');
var Commonservice_1 = require('./Commonservice');
var TeamMaker = (function () {
    function TeamMaker(el, renderer, dialog, services) {
        this.el = el;
        this.renderer = renderer;
        this.dialog = dialog;
        this.services = services;
        this.dia = teamregistration_1.TeamRegistration;
        this.record = {};
        this.categorylist = ['B', 'G'];
        this.agegroup = [];
        this.memeberlist = [];
        this.captianList = [];
    }
    TeamMaker.prototype.addMember = function (e) {
    };
    TeamMaker.prototype.openDialog = function (e) {
        var dialogRef = this.dialog.open(teamregistration_1.TeamRegistration);
        dialogRef.afterClosed().subscribe(function (result) {
            // this.selectedOption = result;
        });
    };
    TeamMaker.prototype.populateages = function (e) {
        this.agegroup = [];
        if (this.selectedcategory != undefined && this.selecteddecipline != undefined) {
            this.agegroup = this.services.arrlist[this.selecteddecipline][this.selectedcategory];
        }
    };
    TeamMaker.prototype.editfn = function (e, member) {
    };
    TeamMaker.prototype.deletefn = function (e, member) {
    };
    TeamMaker.prototype.getCaptianList = function (e) {
        this.captianList = [];
    };
    TeamMaker = __decorate([
        core_1.Component({
            selector: 'team-maker',
            templateUrl: 'app/teammaker.html',
            providers: [Commonservice_1.Commonservice]
        }), 
        __metadata('design:paramtypes', [core_1.ElementRef, core_1.Renderer, material_1.MdDialog, Commonservice_1.Commonservice])
    ], TeamMaker);
    return TeamMaker;
}());
exports.TeamMaker = TeamMaker;
//# sourceMappingURL=teammakerpage.js.map
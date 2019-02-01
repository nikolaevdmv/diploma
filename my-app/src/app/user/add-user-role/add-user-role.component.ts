import {Component, Inject, OnInit, QueryList, ViewChildren} from '@angular/core';
// import {MAT_DIALOG_DATA, MatCheckboxChange, MatDialogRef} from '@angular/material';
import {Role} from '../../shared/model/role/role.model';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';

@Component({
  selector: 'app-add-user-role',
  templateUrl: './add-user-role.component.html',
  styleUrls: ['./add-user-role.component.css']
})
export class AddUserRoleComponent implements OnInit {

  userRoleEnum = Role;
  userRole;
  user: BriefUserRoles;

  @ViewChildren('myCheckbox') private myCheckboxes: QueryList<any>;

  // constructor(public dialogRef: MatDialogRef<AddUserRoleComponent>,
  //             @Inject(MAT_DIALOG_DATA) public data: any) {
  //   this.userRole = Object.keys(this.userRoleEnum);
  //   this.userRole = this.userRole.slice(this.userRole.length / 2).filter(value => value !== 'CREATOR' && value !== 'PARTICIPANT');
  //   this.user = data.user;
  //   data.result = [];
  // }

  onNoClick(): void {
    // this.dialogRef.close();
  }

  ngOnInit() {
  }

  // onChange(event: MatCheckboxChange, index: number) {
  //   const myCheckboxes = this.myCheckboxes.toArray();
  //
  //   // let counter = 0;
  //   // myCheckboxes.forEach(item => {
  //   //   if (!item.checked) {
  //   //     counter++;
  //   //   }
  //   // });
  //   // if (counter === 4) {
  //   //   myCheckboxes.find(value => value.value === 'PARTICIPANT').disabled = false;
  //   //   myCheckboxes.find(value => value.value === 'PARTICIPANT').checked = true;
  //   // }
  //   // if (event.checked && myCheckboxes[index].value !== 'PARTICIPANT') {
  //   //   myCheckboxes.find(value => value.value === 'PARTICIPANT').checked = false;
  //   //   myCheckboxes.find(value => value.value === 'PARTICIPANT').disabled = true;
  //   // }
  //   //
  //
  //
  //   if (myCheckboxes[index].checked) {
  //     this.data.result.push(this.userRoleEnum[myCheckboxes[index].value]);
  //   } else if (!myCheckboxes[index].checked) {
  //     this.data.result.splice(this.data.result.indexOf(this.userRoleEnum[myCheckboxes[index].value]), 1);
  //   }

  // }

}

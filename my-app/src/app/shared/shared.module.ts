import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {FileUploadComponent} from './file-upload/file-upload.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {WidgetsModule} from '../widgets/widgets.module';

@NgModule({
  imports: [
    ReactiveFormsModule,
    CommonModule,
  ],
  exports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule,

    WidgetsModule,

    FileUploadComponent
  ],
  declarations: [FileUploadComponent],
})
export class SharedModule {
}

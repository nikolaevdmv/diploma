import {NgModule} from '@angular/core';
import {HomeComponent} from './home.component';
import {RouterModule} from '@angular/router';
import {HOME_ROUTING} from './home.routing';

@NgModule({
  imports: [RouterModule.forChild(HOME_ROUTING)],
  declarations: [HomeComponent],
  exports: []
})
export class HomeModule {
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Page1Component } from './page1/page1.component';
import { Page2Component } from './page2/page2.component';

const routes: Routes = [
  { path: '', redirectTo: '/page1', pathMatch: 'full' }, // Redirect the root URL to /page1
  { path: 'page1', component: Page1Component }, // Route to the Page1Component
  { path: 'page2', component: Page2Component }, // Route to the Page2Component
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

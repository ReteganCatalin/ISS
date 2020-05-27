import {AfterViewInit, ChangeDetectorRef, Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, AfterViewInit{
  @ViewChild('addConferenceForm', { read: ViewContainerRef }) entry: ViewContainerRef;
  constructor(private resolver: ComponentFactoryResolver,
              private changeDetectorRef: ChangeDetectorRef) { }

  ngOnInit(): void {
  }
  ngAfterViewInit() {
    const formFormFactory = this.resolver.resolveComponentFactory(AddConferenceComponent);
    this.entry.createComponent(formFormFactory);
    this.changeDetectorRef.detectChanges(); // nu stiu ce face line asta, da trebe ca sa nu isi dea naspa :/

  }

}

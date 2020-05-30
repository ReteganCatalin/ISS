import {
  AfterViewInit,
  ApplicationRef,
  ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';
import {ConferenceService} from "../shared/services/conference.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, AfterViewInit{
  @ViewChild('addConferenceForm', { read: ViewContainerRef }) entry: ViewContainerRef;
  conferenceList : Array<any>;
  visible: Number;

  constructor(private resolver: ComponentFactoryResolver,
              private changeDetectorRef: ChangeDetectorRef,
              private conferenceService: ConferenceService,
              private applicationRef: ApplicationRef) {
    this.conferenceList = [];
    this.visible = 0;
  }

  ngOnInit(): void {
    this.conferenceService.getConferenceNamesWithIDs().subscribe(response => {
      this.conferenceList = response;
    })
  }
  ngAfterViewInit() {
    const formFormFactory = this.resolver.resolveComponentFactory(AddConferenceComponent);
    this.entry.createComponent(formFormFactory);
    this.changeDetectorRef.detectChanges(); // nu stiu ce face line asta, da trebe ca sa nu isi dea naspa :/
  }

  selectVisible(i) {
    this.visible = i;
  }
}

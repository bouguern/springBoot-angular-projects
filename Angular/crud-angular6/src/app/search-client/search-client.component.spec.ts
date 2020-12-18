import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchClientComponent } from './search-client.component';

describe('SearchClientComponent', () => {
  let component: SearchClientComponent;
  let fixture: ComponentFixture<SearchClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
